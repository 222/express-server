package com.express.server.model;

import com.express.server.common.BaseModel;

/**
 * @author youping.tan
 * @date 2024/8/6 10:21
 */
public class User extends BaseModel {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 手机号
     */
    private String phone;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
