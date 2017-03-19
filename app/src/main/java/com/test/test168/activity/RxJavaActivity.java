package com.test.test168.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.xian.common.utils.XLog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 在这个Activity学习使用RxAndroid
 */

/***
 * Attention：
 * 1.使用RxBinding的时候，要同时引入支持包
 * 2.
 */
public class RxJavaActivity extends BaseActivity {


    @BindView(R.id.btn_test1)
    Button btn_click;

    @BindView(R.id.btn_test2)
    Button btn_click2;

    @BindView(R.id.btn_test3)
    Button btn_click3;

    @BindView(R.id.btn_test4)
    Button btn_click4;

    @BindView(R.id.tv_test1)
    TextView tv_test1;

    @BindView(R.id.tv_test2)
    TextView tv_test2;

    private Context mContext;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);  //
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        initView();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        stopLoop();
    }

    private void initView() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        tv_test1.setText("runnable + handle的轮询器：\n" + count++ + "");
        tv_test2.setText(R.string.rx_timer);
        handler.postDelayed(runnable, 1000); // 开始Timer

//        // rx 的点击事件
//        RxView.clicks(btn_click2)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//                        handler.removeCallbacks(runnable);
//                    }
//                });
//        RxView.clicks(btn_click3)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//                        handler.postDelayed(runnable, 1000);
//                    }
//                });
//
//        // Rx
//        // 防止重复点击
//        RxView.clicks(btn_click)
//                .throttleFirst(interval, TimeUnit.SECONDS)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//                        T.showShort(mContext, interval + "秒后就可以点击一次");
//                    }
//                });

        // 轮询器启动
        startLoop();
        tv_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLoop();
                startLoop();
            }
        });
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
