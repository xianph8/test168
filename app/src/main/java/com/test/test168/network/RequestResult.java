package com.test.test168.network;

/**
 * Created by w07 on 2016/7/28.
 * 网络请求返回结果的封装类
 */
public class RequestResult<T> {

    private String Message;
    private boolean Success;
    private int Level;
    private T Data;
    private int resultCode;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

}
