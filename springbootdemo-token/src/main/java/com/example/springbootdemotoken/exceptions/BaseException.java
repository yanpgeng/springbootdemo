package com.example.springbootdemotoken.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 异常基类
 * @author zhaozhengyang
 */
public class BaseException extends RuntimeException {

    public static final ObjectMapper JsonUtils = new ObjectMapper();

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
