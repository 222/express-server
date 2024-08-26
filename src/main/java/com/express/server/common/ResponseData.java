package com.express.server.common;

import java.io.Serializable;

/**
 * 统一响应
 *
 * @author tanyp
 * @since 2023/2/21
 */
public class ResponseData<T> implements Serializable {

    public static final ResponseData<Void> SUCCESS = new ResponseData<>();

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
    /**
     * 总条数
     */
    private Long total;

    public ResponseData() {
    }

    public ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(T data, Long total) {
        this.data = data;
        this.total = total;
    }

    public ResponseData(T data) {
        this.data = data;
    }

    public static ResponseData<Void> result(ErrorCodes codes) {
        int code = codes.getCode();
        String msg = codes.getMsg();
        return new ResponseData<>(code, msg);
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
