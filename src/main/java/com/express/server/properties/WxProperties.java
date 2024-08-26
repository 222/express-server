package com.express.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author youping.tan
 * @date 2024/5/10 10:07
 */
@Configuration
@ConfigurationProperties(
        prefix = "wx",
        ignoreUnknownFields = false
)
public class WxProperties {
    /**
     * 应用唯一标识，在微信开放平台提交应用审核通过后获得
     */
    private String appId;
    /**
     * 应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
     */
    private String appSecret;
    /**
     * 重定向地址
     */
    private String redirectUri;
    /**
     * 登录回调
     */
    private String loginCallback;
    /**
     * 绑定回调
     */
    private String bindCallback;
    /**
     * 解绑回调
     */
    private String unbindCallback;
    /**
     * 请求授权地址
     */
    private String authorize;
    /**
     * 获取 access_token 接口地址
     */
    private String accessToken;
    /**
     * 获取用户信息接口地址
     */
    private String userinfo;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getLoginCallback() {
        return loginCallback;
    }

    public void setLoginCallback(String loginCallback) {
        this.loginCallback = loginCallback;
    }

    public String getBindCallback() {
        return bindCallback;
    }

    public void setBindCallback(String bindCallback) {
        this.bindCallback = bindCallback;
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getUnbindCallback() {
        return unbindCallback;
    }

    public void setUnbindCallback(String unbindCallback) {
        this.unbindCallback = unbindCallback;
    }
}
