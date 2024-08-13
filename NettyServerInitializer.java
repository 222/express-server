package com.express.netty;

import com.championlang.netty.codec.ObjDecoder;
import com.championlang.netty.codec.ObjEncoder;
import com.championlang.netty.common.MessageHandlerFactory;
import com.championlang.netty.handler.ServerIdleStateTrigger;
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
        ChannelPipeline ph = channel.pipeline();
        //对象传输处理[解码]
        ph.addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法
        //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
        ph.addLast(new IdleStateHandler(60, 0, 0));
        ph.addLast(new ServerIdleStateTrigger());
//        channel.pipeline().addLast(new HeartbeatHandler());
        ph.addLast(new NettyServerHandler(messageHandlerFactory));
        //对象传输处理[编码]
        ph.addLast(new ObjEncoder());
    }
}
