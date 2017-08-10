package org.wzs.springbootdemo.exception;

import java.util.Objects;

public class MyException extends RuntimeException implements IError {

    private int code = -1;
    private String msg = "";

    public MyException(String msg) {
        Objects.requireNonNull(msg);
        this.msg = msg;
    }

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
