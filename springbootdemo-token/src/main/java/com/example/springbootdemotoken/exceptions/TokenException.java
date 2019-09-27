package com.example.springbootdemotoken.exceptions;


import com.example.springbootdemotoken.consts.SysConst;

public class TokenException extends BaseException {

    /**
     * 异常码
     */
    private int code;
    /**
     * 异常信息
     */
    private String message;

    public TokenException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public TokenException(SysConst cnt) {
        this.code = cnt.getCode();
        this.message = cnt.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
