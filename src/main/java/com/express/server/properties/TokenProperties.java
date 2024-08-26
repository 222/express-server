package com.express.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author youping.tan
 * @date 2024/8/6 16:14
 */
@Configuration
@ConfigurationProperties(
        prefix = "token",
        ignoreUnknownFields = false
)
public class TokenProperties {
    private String secret;
    private Integer expire;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }
}
