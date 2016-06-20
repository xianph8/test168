package com.test.test168.event;

/**
 * Created by King on 2016/3/26.
 */
public class BaseEventObject {

    private String msg = "";
    private boolean isSuccess = false;
    private Exception exception = null;
    private int code = -1;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
