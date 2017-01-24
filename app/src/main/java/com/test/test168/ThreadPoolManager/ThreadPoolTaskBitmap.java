package com.test.test168.ThreadPoolManager;

/**
 * Created by w07 on 2017/1/24 14:48
 * Description :
 */

import android.graphics.Bitmap;
import android.util.Log;
import android.os.Process;
import android.view.View;

/**
 * 图片加载的任务单元
 * @author carrey
 *
 */
public class ThreadPoolTaskBitmap extends ThreadPoolTask {

    private static final String TAG = "ThreadPoolTaskBitmap";

    private CallBack callBack;

    private View view;

    private int position;

    public ThreadPoolTaskBitmap(String url, CallBack callBack, int position, View view) {
        super(url);
        this.callBack = callBack;
        this.position = position;
        this.view = view;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);

        Bitmap bitmap = ImageHelper.loadBitmapFromNet(url);

        Log.i(TAG, "loaded: " + url);

        if (callBack != null) {
            callBack.onReady(url, bitmap, this.position, this.view);
        }
    }

    public interface CallBack {
        public void onReady(String url, Bitmap bitmap, int position, View view);
    }
}
