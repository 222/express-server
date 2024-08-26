package com.express.server.netty.codec;

import com.express.server.netty.common.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 粘包
 * @author tanyp
 * @since 2023/2/27 10:14
 */
public class ObjEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) {
        byte[] data = ProtostuffUtil.serialize(msg);
        out.writeInt(data.length);
        out.writeBytes(data);
    }

}
