package com.express.server.param.req;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * @author youping.tan
 * @date 2024/8/6 10:34
 */
public class UserLoginDTO {
    /**
     * 手机号
     */
    @NotBlank(message = "账号不能为空")
    @Length(min = 11, max = 11, message = "账号非法")
    private String phone;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Length(min = 4, max = 6, message = "验证码非法")
    private String verifyCode;

    public @NotBlank(message = "账号不能为空") @Length(min = 11, max = 11, message = "账号非法") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "账号不能为空") @Length(min = 11, max = 11, message = "账号非法") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "验证码不能为空") @Length(min = 4, max = 6, message = "验证码非法") String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(@NotBlank(message = "验证码不能为空") @Length(min = 4, max = 6, message = "验证码非法") String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
