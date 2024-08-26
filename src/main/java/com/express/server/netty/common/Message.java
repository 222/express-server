package com.express.server.netty.common;


import java.io.Serializable;
import java.util.Map;

/**
 * @author youping.tan
 * @date 2024/8/5 14:19
 */
public class Message implements Serializable {

    private String type;
    private Map<String, Object> payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}
