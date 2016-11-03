package com.test.test168.IntentService;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * Created by w07 on 2016/10/20.
 */

public class BaseIntentService extends IntentService {

    protected Context mContext = null;

    public BaseIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

}
