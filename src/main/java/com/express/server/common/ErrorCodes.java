package com.express.server.common;

import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author tanyp
 * @since 2023/2/21
 */
public enum ErrorCodes {
    /**
     * 系统繁忙
     */
    BUSY(-1, "系统繁忙"),
    /**
     * 注销成功
     */
    LOGOUT_SUCCESSFUL(0, "注销成功"),
    SUCCESS(0, "请求成功"),


    /**
     * 预留1000个用户相关提示
     * 40000-41000
     */
    LOGIN_SUCCESSFUL(0, "登录成功"),
    LOGIN_FAIL(-1, "登录失败"),

    LOGIN_EXPIRED(40001, "由于您长时间未操作，请重新登录!"),
    BE_REPLACED(40002, "您已在另一台设备登录，本次登录已下线!"),
    KICK_OUT(40003, "您已被强制下线，请重新登录!"),
    LOGIN_CREDENTIALS_EXPIRED(40004, "密码过期，请联系管理员!"),
    LOGIN_ACCOUNT_EXPIRED(40005, "账户过期，请联系管理员!"),
    LOGIN_CAPTCHA_BAD_CREDENTIALS(40006, " 手机号或者验证码输入错误，请重新输入!"),
    LOGIN_BAD_CREDENTIALS(4007, "用户名或者密码输入错误，请重新输入!"),
    LOGIN_DISABLED(40008, "账户被禁用，请联系管理员!"),
    LOGIN_LOCKED(40009, "账户被锁定，请联系管理员!"),

    LOGIN_WX_BAD_CREDENTIALS(40010, "微信登录异常"),
    LOGIN_QQ_BAD_CREDENTIALS(40011, "QQ登录异常"),
    PERMISSION(40012, "权限不足"),
    ILLEGAL_ACCESS(40013, "非法访问"),
    HTTP_METHOD(40014, "请求方式有误"),
    RATE_LIMIT(40015, "限流了，请稍后重试！"),
    DELETE_FAILED(40016, "删除失败"),
    MAX_UPLOAD_SIZE_EXCEEDED(40017, "上传的文件过大"),

    ID_INVALID(40018, "编辑id不可为空"),

    /**
     * 异常相关提示
     * 41001-49999
     */
    DATA_FORMAT(41001, "入参格式有误"),
    REQ_METHOD(41002, "请求方式有误"),
    NOT_FOUND(41003, "请求地址有误"),
    SERVICE_UNAVAILABLE(41005, "服务暂时不可用，请稍后再试"),
    RPC_FAIL(41006, "远程调用异常"),
    SMS_SEND_FAIL(41008, "短信发送失败"),
    MAIL_SEND_FAIL(41009, "邮件发送失败"),
    ILLEGAL_LOGIN(41010, "非法登录客户端"),
    ROLE_NON_EXIST(41011, "角色不存在"),
    ROLE_CANNOT_DELETE(41012, "角色存在深层关联，无法删除"),
    NAME_REPEATED(41013, "不可重复"),
    VALID_ANOMALY(50001, "参数校验异常"),
    ;

    private final int code;
    private final String msg;

    ErrorCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String resultMap(ErrorCodes errorCodes) {
        return resultMap(errorCodes, null);
    }

    public static String resultMap(ErrorCodes errorCodes, Object o) {
        Map<String, Object> resultMap = new HashMap<>(3);
        if (o != null) {
            resultMap.put("data", o);
        }
        resultMap.put("code", errorCodes.code);
        resultMap.put("msg", errorCodes.msg);
        return JSON.toJSONString(resultMap);
    }

    public static String resultMap(int code, String msg) {
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        return JSON.toJSONString(resultMap);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
