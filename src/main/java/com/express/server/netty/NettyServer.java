package com.express.server.netty;

import com.express.server.netty.common.MessageHandlerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * 服务端启动类
 *
 * @author tanyp
 * @since 2023/2/27 10:14
 */
@Service("nettyServer")
public class NettyServer {

    // 使用 SLF4J 日志框架记录日志
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    // 创建负责接受客户端连接的主线程组，通常只需要一个线程
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    // 创建负责处理客户端 I/O 操作的工作线程组
    private final EventLoopGroup workGroup = new NioEventLoopGroup();

    // 注入自定义消息处理器工厂，用于创建消息处理器
    @Resource
    private MessageHandlerFactory messageHandlerFactory;

    // 注入 Netty 配置类，包含服务器的配置参数（如主机和端口）
    @Resource
    private NettyConfig nettyConfig;

    // 记录 Netty 服务器的通道操作结果
    private ChannelFuture channelFuture;

    // 初始化方法，配置并启动 Netty 服务器
    @PostConstruct
    public void start() throws InterruptedException {
        String host = nettyConfig.getHost();  // 获取服务器主机地址
        int port = nettyConfig.getPort();     // 获取服务器端口号
        try {
            ServerBootstrap b = new ServerBootstrap();  // 创建 Netty 服务器启动器
            b.group(bossGroup, workGroup)                // 绑定主线程组和工作线程组
                    .channel(NioServerSocketChannel.class)   // 指定通道类型为 NIO 服务器通道
                    .option(ChannelOption.SO_BACKLOG, 128)   // 设置 TCP 参数，指定等待连接队列的大小
                    .childHandler(new NettyServerInitializer(messageHandlerFactory)); // 设置子通道处理器

            // 绑定服务器到指定的主机和端口，并同步等待绑定完成
            channelFuture = b.bind(new InetSocketAddress(host, port)).sync();
        } finally {
            // 如果通道成功绑定，记录日志；否则记录错误信息
            if (Objects.nonNull(channelFuture) && channelFuture.isSuccess()) {
                logger.info("Netty Server started on port {}:{}", host, port);
            } else {
                logger.error("Netty Server start error.");
            }
        }
    }

    // 关闭服务器的方法
    @PreDestroy
    public void shutdown() throws InterruptedException {
        try {
            // 等待通道关闭操作完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭主线程组和工作线程组并释放资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
