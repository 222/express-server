package com.express.server.netty.handler;

import com.express.server.netty.common.ChannelPool;
import com.express.server.netty.common.Message;
import com.express.server.netty.common.MessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author youping.tan
 * @date 2024/8/5 14:20
 */
@Component("10001")
public class LoginHandler implements MessageHandler {

    @Override
    public void handle(Channel channel, Message message) {
        Map<String, Object> payload = message.getPayload();
        Long memberId = (Long) payload.get("phone");
        ChannelPool.add(memberId, channel);

        String msg = "欢迎[" + memberId + "]加入群聊";
        Message sendMsg = new Message();
        sendMsg.setType("10002");
        sendMsg.setPayload(Map.of("msg", msg));
        ChannelPool.sendToAll(sendMsg);
    }

}
