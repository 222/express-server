package com.express.server.common;

/**
 * 自定义全局异常
 * @author tanyp
 * @since 2023/2/21
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5425794447623371924L;

    private final Integer code;

    public BusinessException(String msg) {
        super(msg);
        this.code = -1;
    }

    public BusinessException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public BusinessException(ErrorCodes errorCodes) {
        this.code = errorCodes.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
