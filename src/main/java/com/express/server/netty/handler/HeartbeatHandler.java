package com.express.server.netty.handler;

import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tanyp
 * @since 2023/2/27 10:14
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        // 发送心跳消息，并在发送失败时关闭该连接
//        if (evt instanceof IdleStateEvent) {
//            InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
//            String clientIp = ipSocket.getAddress().getHostAddress();
//            if (!ctx.channel().isActive()) {
//                logger.info("客户端:{} unActive", clientIp);
//            }
//            if (!ctx.channel().isOpen()) {
//                logger.info("客户端:{} closed", clientIp);
//            }
//            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
//            heartbeatRequest.setSuccess(true);
//            heartbeatRequest.setUserHead("userhead");
//            heartbeatRequest.setUserNickName("nickname");
//            heartbeatRequest.setUserId("userid");
//
//            throw new RuntimeException("xxxxxxxxx");
//
////            ctx.channel().writeAndFlush(heartbeatRequest);
//
////            logger.info("给:{}客户端发送心跳 成功", clientIp);
//        } else {
//            // 不是IdleStateEvent事件，所以将它传递给下一个ChannelInboundHandler
//            super.userEventTriggered(ctx, evt);
//        }
//    }
}
