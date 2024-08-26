package com.express.server.common;

/**
 * @author youping.tan
 * @date 2024/8/6 14:51
 */
public class LoginSession {
    private Long memberId;
    private String phone;
    private String realName;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
