package com.test.test168.bean;

public class Response<T> {

   private int code;
   private T data;
   private String message;

    public boolean isSuccess() {
        return code == 200;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
