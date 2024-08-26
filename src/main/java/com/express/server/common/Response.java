package com.express.server.common;

import java.io.Serializable;

/**
 * 统一响应
 *
 * @author tanyp
 * @since 2023/2/21
 */
public class Response<T> implements Serializable {

    public static final Response<Void> SUCCESS = new Response<>();

    /**
     * 返回码
     */
    private int code = 200;
    /**
     * 说明
     */
    private String msg = "success";
    /**
     * 响应数据
     */
    private T data;

    public Response() {
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(T data) {
        this.data = data;
    }

    public static Response<Void> result(ErrorCodes codes) {
        int code = codes.getCode();
        String msg = codes.getMsg();
        return new Response<>(code, msg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
