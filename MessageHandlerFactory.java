package com.championlang.netty.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youping.tan
 * @date 2024/8/5 14:19
 */
@Component
public class MessageHandlerFactory {

    private final Map<String, MessageHandler> handlers;

    @Autowired
    public MessageHandlerFactory(Map<String, MessageHandler> handlers) {
        this.handlers = new HashMap<>(handlers);
    }

    public MessageHandler getHandler(String type) {
        return handlers.get(type);
    }
}
