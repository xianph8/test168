package com.test.test168.rx;

import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.xian.common.utils.T;
import com.xian.common.utils.XLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxViewActivity extends BaseActivity {


    Button btn_click;

    Button btn_click2;

    Button btn_click3;

    Button btn_click4;

    TextView tv_test1;

    TextView tv_test2;

    private Disposable subscribe;
    private int interval = 4;
    private int count = 0;

    // 简单的定时器
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            // TODO: todo update UI or other something
            tv_test1.setText(tv_test1.getText().toString() + "、" + count++);
            handler.postDelayed(this, 1000);
            //postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
        }
    };
//    handler.postDelayed(runnable,1000); // 开始Timer
//    handler.removeCallbacks(runnable); //停止Timer

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_rx_view);
        btn_click = findViewById(R.id.btn_test1);
        btn_click2 = findViewById(R.id.btn_test2);
        btn_click3 = findViewById(R.id.btn_test3);
        btn_click4 = findViewById(R.id.btn_test4);
        tv_test1 = findViewById(R.id.tv_test1);
        tv_test2 = findViewById(R.id.tv_test2);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        tv_test1.setText("runnable + handle的轮询器：\n" + count++ + "");
        tv_test2.setText(R.string.rx_timer);
        handler.postDelayed(runnable, 1000); // 开始Timer

//        // rx 的点击事件
        RxView.clicks(btn_click2)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        handler.removeCallbacks(runnable);
                    }
                });
        RxView.clicks(btn_click3)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object aVoid)throws Exception {
                        handler.postDelayed(runnable, 1000);
                    }
                });

        // Rx
        // 防止重复点击
        RxView.clicks(btn_click)
                .throttleFirst(interval, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object aVoid) {
                        T.showShort(mActivity, interval + "秒后就可以点击一次");
                    }
                });

        // 轮询器启动
        startLoop();
        RxView.clicks(tv_test2).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                stopLoop();
                startLoop();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        stopLoop();
    }

    // Rx 的轮询器
    // 启动轮询
    void startLoop() {
        XLog.i("start loop :");
        if (null == subscribe || subscribe.isDisposed()) {
            subscribe = Observable.interval(0, interval, TimeUnit.SECONDS)
                    //延时0 ，每间隔interval，时间单位 秒
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) {
                            interval--;
//                            tv_test2.setText(tv_test2.getText() + "\n现在点击没用，还有" + interval + "秒才可以再点击！");
                            tv_test2.setText(tv_test2.getText().toString() + "\n" + interval + "、" + interval + "秒一次，点击文字重新跑");
                            if (interval == 0) {
                                stopLoop();
                            }
                        }
                    });
        }
    }

    // 停止轮询
    void stopLoop() {
        XLog.i("stop loop :");
        if (null != subscribe && !subscribe.isDisposed()) {
            interval = 4;
            subscribe.dispose();
        }
    }
}
