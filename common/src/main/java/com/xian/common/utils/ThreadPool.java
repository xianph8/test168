package com.xian.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by w07 on 2016/12/23 13:51
 * Description : 线程池管理类
 */
public class ThreadPool {

    private ExecutorService service;

    private ThreadPool() {
//        service = Executors.newSingleThreadExecutor(); //单一线程的线程池
//        service = Executors.newFixedThreadPool(3);     //固定数量的线程池
        service = Executors.newCachedThreadPool();     //无界线程池，可以进行自动线程回收
    }

    private static class SingletonHolder {
        static ThreadPool instance = new ThreadPool();
    }

    public static ThreadPool getInstance() {
        return SingletonHolder.instance;
    }

    public void executeTask(Runnable runnable) {
        service.execute(runnable);
    }

    public void shoutDown() {
        if (!service.isShutdown()) {
            service.shutdown();
        }
    }

}
