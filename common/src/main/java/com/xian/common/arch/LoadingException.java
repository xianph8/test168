package com.xian.common.arch;

import android.os.Build;
import android.support.annotation.RequiresApi;


public class LoadingException extends Exception {
    public LoadingException() {
    }

    public LoadingException(String message) {
        super(message);
    }

    public LoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingException(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LoadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
