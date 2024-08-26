package com.express.server.param.req;

/**
 * @author youping.tan
 * @date 2024/8/6 10:34
 */
public class UserRegisterDTO {
    private Integer phone;
    private String password;

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
