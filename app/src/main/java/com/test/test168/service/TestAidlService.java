package com.test.test168.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.test.test168.IFirstAidlInterface;

/**
 * @author xian
 */
public class TestAidlService extends Service {
    private final IFirstAidlInterface.Stub mBinder = new IFirstAidlInterface.Stub() {
        @Override
        public int compute(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    public TestAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
