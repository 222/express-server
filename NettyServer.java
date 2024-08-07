package com.express.netty;

import com.championlang.netty.common.MessageHandlerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * 服务端启动类
 *
 * @author youping.tan
 * @since 2024/8/7 16:31
 */
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private final EventLoopGroup workGroup = new NioEventLoopGroup();
    @Resource
    private MessageHandlerFactory messageHandlerFactory;
    @Resource
    private NettyConfig nettyConfig;

    private ChannelFuture channelFuture;

    public void init() throws InterruptedException {
        String host = nettyConfig.getHost();
        int port = nettyConfig.getPort();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new NettyServerInitializer(messageHandlerFactory));
            channelFuture = b.bind(new InetSocketAddress(host, port)).sync();
        } finally {
            if (Objects.nonNull(channelFuture) && channelFuture.isSuccess()) {
                logger.info("Netty Server started on port {}:{}", host, port);
            } else {
                logger.error("Netty Server start error. ");
            }
        }
    }

    public void shutdown() throws InterruptedException {
        try {
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
