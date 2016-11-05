package com.test.test168.service;

import android.content.Intent;

import com.test.test168.utils.L;

public class TestIntentService extends BaseIntentService {

    private long time = 0;

    public TestIntentService() {
        super("TestIntentService");
    }

    public TestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            while (true) {
                try {
                    time++;
                    L.i("sleep : " + time);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    L.e("exception : " + e);
                }
            }
        }
    }

}
