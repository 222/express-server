package com.express.server.common;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import java.util.List;

/**
 * 统一异常处理类
 *
 * @author tanyp
 * @since 2023/2/21
 */
@ConditionalOnClass(DispatcherServlet.class)
@RestControllerAdvice
@Order(2)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Resource
    private MessageSource messageSource;

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<Void> exception(Exception e) {
        LOGGER.error("未知异常拦截：", e);
        return ResponseData.result(ErrorCodes.BUSY);
    }

    /**
     * HTTP方法异常拦截处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseData<Void> exception(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("HTTP方法异常拦截：", e);
        return ResponseData.result(ErrorCodes.HTTP_METHOD);
    }

    /**
     * 入参格式异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseData<Void> exception(HttpMessageNotReadableException e) {
        LOGGER.error("入参格式有误：", e);
        return ResponseData.result(ErrorCodes.DATA_FORMAT);
    }

    /**
     * 入参类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseData<Void> exception(MethodArgumentTypeMismatchException e) {
        LOGGER.error("入参类型不匹配：", e);
        return ResponseData.result(ErrorCodes.DATA_FORMAT);
    }


    /**
     * 404异常拦截处理
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseData<Void> exception(NoHandlerFoundException e) {
        LOGGER.error("404异常拦截：", e);
        return ResponseData.result(ErrorCodes.NOT_FOUND);
    }


    /**
     * 自定义异常拦截处理
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<Void> exception(BusinessException e) {
        String code = String.valueOf(e.getCode());
        String message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        LOGGER.error("主动异常拦截：", e);
        return new ResponseData<>(e.getCode(), message);
    }

    /**
     * 校验异常拦截处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<Void> expHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErrorCodes errorCodes = ErrorCodes.VALID_ANOMALY;
        ResponseData<Void> responseData = new ResponseData<>();
        BindingResult result = e.getBindingResult();
        LOGGER.error("请求[ {} ] {} 参数校验发生错误", request.getMethod(), request.getRequestURL());
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            responseData.setCode(errorCodes.getCode());
            StringBuilder sb = new StringBuilder();
            sb.append(errorCodes.getMsg());
            sb.append("：");
            for (int i = 0; i < errors.size(); i++) {
                FieldError fieldError = (FieldError) errors.get(i);
                LOGGER.error("入参 {} = {} 校验错误：{}", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
                sb.append(fieldError.getDefaultMessage());
                if (i < (errors.size() - 1)) {
                    sb.append(",");
                }
            }
            responseData.setMsg(sb.toString());
        }
        return responseData;
    }

    /**
     * 远程调用失败
     */
    @ExceptionHandler(NestedServletException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<Void> exception(NestedServletException e) {
        LOGGER.error("远程调用失败：", e);
        return ResponseData.result(ErrorCodes.RPC_FAIL);
    }

    /**
     * 上传的文件过大
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<Void> exception(MaxUploadSizeExceededException e) {
        LOGGER.error("文件上传过大返回异常信息：", e);
        return ResponseData.result(ErrorCodes.MAX_UPLOAD_SIZE_EXCEEDED);
    }

}
