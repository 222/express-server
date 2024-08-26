package com.express.server.netty.codec;

import com.express.server.netty.common.Message;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * ProtostuffUtil
 *
 * @author tanyp
 * @since 2023/2/27 10:14
 */
public class ProtostuffUtil {

    private ProtostuffUtil() {
    }

    private static final Schema<Message> schema = RuntimeSchema.getSchema(Message.class);

    public static byte[] serialize(Message message) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        return ProtostuffIOUtil.toByteArray(message, schema, buffer);
    }

    public static Message deserialize(byte[] data) {
        Message message = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, message, schema);
        return message;
    }
}
