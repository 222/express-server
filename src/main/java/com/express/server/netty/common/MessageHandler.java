package com.express.server.netty.common;

import io.netty.channel.Channel;

/**
 * @author youping.tan
 * @date 2024/8/5 14:20
 */
public interface MessageHandler {

    void handle(Channel channel, Message msg);

}
