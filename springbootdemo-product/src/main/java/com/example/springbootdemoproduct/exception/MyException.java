package com.example.springbootdemoproduct.exception;


/**
 * @author YangPeng
 * @Title: MyException
 * @ProjectName springbootdemo
 * @Description: TODO
 * @date 2019/3/28-17:36
 */
public class MyException extends RuntimeException {
    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String code;
    private String msg;

}
