package com.express.server.netty.common;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tanyp
 * @since 2023/2/27 10:14
 */
public class ChannelPool {

    private ChannelPool() {
    }

    private static final Map<Long, Channel> CHANNELMAP = new ConcurrentHashMap<>();

    public static void add(Long memberId, Channel channel) {
        CHANNELMAP.put(memberId, channel);
    }

    public static void remove(Long memberId) {
        CHANNELMAP.remove(memberId);
    }

    public static void remove(ChannelId channelId) {
        CHANNELMAP.entrySet().removeIf(entry -> entry.getValue().id().equals(channelId));
    }

    public static synchronized void sendToAll(Message sendMsg) {
        for (Channel channel : CHANNELMAP.values()) {
            channel.writeAndFlush(sendMsg);
        }
    }

    public static synchronized void sendToOne(Message sendMsg, Long memberId) {
        Channel channel = CHANNELMAP.get(memberId);
        if (Objects.nonNull(channel)){
            channel.writeAndFlush(sendMsg);
        }
    }
}
