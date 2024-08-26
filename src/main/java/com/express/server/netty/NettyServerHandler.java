package com.express.server.netty;

import com.express.server.netty.common.ChannelPool;
import com.express.server.netty.common.Message;
import com.express.server.netty.common.MessageHandler;
import com.express.server.netty.common.MessageHandlerFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * 服务端逻辑处理类
 *
 * @author tanyp
 * @since 2023/2/27 10:14
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);

    private final MessageHandlerFactory messageHandlerFactory;

    public NettyServerHandler(MessageHandlerFactory messageHandlerFactory) {
        this.messageHandlerFactory = messageHandlerFactory;
    }

    /**
     * 注册事件
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        LOGGER.info("客户端 {} 正在注册", clientIp);
    }

    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws UnknownHostException {
        // 为新连接发送庆祝
        LOGGER.info("客户端连接通知：{}", ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message msg) {
        try {
            MessageHandler handler = messageHandlerFactory.getHandler(msg.getType());
            if (Objects.nonNull(handler)) {
                handler.handle(ctx.channel(), msg);
            } else {
                ctx.channel().writeAndFlush("Unknown type");
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 失去链接时触发
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        LOGGER.info("{} 已离线", clientIp);
        ChannelPool.remove(ctx.channel().id());
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("exceptionCaught ip:{}", ctx.channel().remoteAddress(), cause);
        ChannelPool.remove(ctx.channel().id());
    }
}
