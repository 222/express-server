package com.express.server.netty.handler;

import com.alibaba.fastjson2.JSON;
import com.express.server.netty.common.Message;
import com.express.server.netty.common.MessageHandler;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author youping.tan
 * @date 2024/8/5 14:20
 */
@Component("10003")
public class GreetingHandler implements MessageHandler {

    @Override
    public void handle(Channel channel, Message message) {
        Map<String, Object> payload = message.getPayload();
        System.out.println(JSON.toJSONString(payload));
//
//        Response<String> response = new Response<>();
//        response.setData("Hello," + name);
//        Map<String, Object> result = BeanUtil.beanToMap(response);
//        Message respMessage = new Message();
//        respMessage.setType(message.getType());
//        respMessage.setPayload(result);
//        channel.writeAndFlush(respMessage);

    }

}
