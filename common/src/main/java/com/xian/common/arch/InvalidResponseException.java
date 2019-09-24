package com.xian.common.arch;

public class InvalidResponseException extends Exception {
    public InvalidResponseException() {
        super();
    }

    public InvalidResponseException(String msg) {
        super(msg);
    }
}
