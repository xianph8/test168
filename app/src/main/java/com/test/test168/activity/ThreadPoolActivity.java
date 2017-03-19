package com.test.test168.activity;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.xian.common.utils.ThreadPool;
import com.xian.common.utils.XLog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolActivity extends BaseActivity {

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_thread_pool);

        ThreadPool.getInstance().executeTask(new Runnable() {
            @Override
            public void run() {
                XLog.i("run run run run : ");
            }
        });

        ExecutorService service0 = Executors.newSingleThreadExecutor(); //单一线程的线程池
        ExecutorService service1 = Executors.newFixedThreadPool(3);     //固定数量的线程池
        ExecutorService service2 = Executors.newCachedThreadPool();     //自动管理线程的线程池

    }
}
