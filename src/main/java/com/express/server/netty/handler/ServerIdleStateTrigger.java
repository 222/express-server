package com.express.server.netty.handler;

import com.express.server.netty.common.ChannelPool;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 超时处理 如60秒没有接受客户端的心跳，就触发; 如果超过两次，则直接关闭;
 *
 * @author tanyp
 * @since 2023/2/27 10:14
 */
public class ServerIdleStateTrigger extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerIdleStateTrigger.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            LOGGER.info("已经60秒没有接收到客户端的信息了");
            InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
            String clientIp = ipSocket.getAddress().getHostAddress();
            LOGGER.info("{} 断开连接", clientIp);
            ctx.disconnect();
            ChannelPool.remove(ctx.channel().id());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
