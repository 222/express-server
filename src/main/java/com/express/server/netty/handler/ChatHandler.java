package com.express.server.netty.handler;

import com.express.server.netty.common.ChannelPool;
import com.express.server.netty.common.Message;
import com.express.server.netty.common.MessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author youping.tan
 * @date 2024/8/5 14:20
 */
@Component("10004")
public class ChatHandler implements MessageHandler {

    @Override
    public void handle(Channel channel, Message message) {
        Map<String, Object> payload = message.getPayload();
        String msg = (String) payload.get("msg");
        Long memberId = (Long) payload.get("memberId");
        Message sendMsg = new Message();
        sendMsg.setType("10001");
        sendMsg.setPayload(Map.of("msg", msg));
        if (Objects.isNull(memberId)) {
            ChannelPool.sendToAll(sendMsg);
        } else {
            ChannelPool.sendToOne(sendMsg, memberId);
        }
    }

}
