package com.test.test168.rx;

import android.os.Bundle;

import com.test.test168.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xian.common.utils.XLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

/**
 * 在这个Activity学习使用RxAndroid
 */

/***
 * Attention：
 * 1.使用RxBinding的时候，要同时引入支持包
 * 2.
 * @author xian
 */
public class RxJavaActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        initView();
    }

    private void initView() {

        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .compose(new ObservableTransformer<Long, Object>() {
                    @Override
                    public ObservableSource<Object> apply(Observable<Long> upstream) {
                        return null;
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });

    }

}
