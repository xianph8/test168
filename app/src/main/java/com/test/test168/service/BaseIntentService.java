package com.test.test168.service;

import android.app.IntentService;
import android.content.Context;

/**
 * Created by w07 on 2016/10/20.
 */

public abstract class BaseIntentService extends IntentService {

    protected Context mContext = null;

    public BaseIntentService(String name) {
        super(name);
    }

}
