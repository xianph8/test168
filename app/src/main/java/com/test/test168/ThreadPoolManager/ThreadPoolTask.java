package com.test.test168.ThreadPoolManager;

/**
 * Created by w07 on 2017/1/24 14:47
 * Description :
 */

public abstract class ThreadPoolTask implements Runnable {

    protected String url;

    public ThreadPoolTask(String url) {
        this.url = url;
    }

    public abstract void run();

    public String getURL() {
        return this.url;
    }
}
