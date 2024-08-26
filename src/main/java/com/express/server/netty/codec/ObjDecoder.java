package com.express.server.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 拆包
 * @author youping.tan
 * @since 2024/8/7 16:35
 */
public class ObjDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 自定义拆包逻辑，例如处理自定义协议的头部和内容
        if (in.readableBytes() < 4) { // 假设包头长度为4字节
            return;
        }
        in.markReaderIndex(); // 标记当前读指针位置
        int length = in.readInt(); // 读取包长度
        if (in.readableBytes() < length) {
            in.resetReaderIndex(); // 如果包还没有完全到达，恢复读指针，等待下次读取
            return;
        }
        byte[] data = new byte[length];
        in.readBytes(data); // 读取一个完整的包
        out.add(ProtostuffUtil.deserialize(data)); // 将解码出的包传递给下一个处理器
    }

}
