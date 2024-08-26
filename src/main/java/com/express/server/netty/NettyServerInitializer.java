package com.express.server.netty;

import com.express.server.netty.codec.ObjDecoder;
import com.express.server.netty.codec.ObjEncoder;
import com.express.server.netty.common.MessageHandlerFactory;
import com.express.server.netty.handler.ServerIdleStateTrigger;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 服务端初始化类
 *
 * @author tanyp
 * @since 2023/2/27 10:14
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    private final MessageHandlerFactory messageHandlerFactory;

    public NettyServerInitializer(MessageHandlerFactory messageHandlerFactory) {
        this.messageHandlerFactory = messageHandlerFactory;
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pl = channel.pipeline();
        //对象传输处理[解码]
        pl.addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法
        //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
        pl.addLast(new IdleStateHandler(60, 0, 0));
        pl.addLast(new ServerIdleStateTrigger());
//        pl.addLast(new HeartbeatHandler());
        pl.addLast(new NettyServerHandler(messageHandlerFactory));
        //对象传输处理[编码]
        pl.addLast(new ObjEncoder());
    }
}