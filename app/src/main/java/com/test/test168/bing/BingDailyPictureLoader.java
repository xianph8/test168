package com.test.test168.bing;

import android.content.Context;

import com.test.test168.api.BingApi;
import com.test.test168.bean.BingDailyPicture;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by King on 2017/3/18.
 */

public class BingDailyPictureLoader {

    private Context mContext;

    public BingDailyPictureLoader(Context mContext) {
        this.mContext = mContext;
    }

    public void load(int index, BingSub<BingDailyPicture> customSub) {
        BingApiWrapper.getInstance()
                .create(BingApi.class)
                .loadDailyPicture(getParams(index))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(customSub);
//                .subscribe(new Observer<BingDailyPicture>() {
//                    @Override
//                    public void onSubscribe(Disposable disposable) {
//                        XLog.i(" disposable : " + disposable);
//                    }
//
//                    @Override
//                    public void onNext(BingDailyPicture bingDailyPicture) {
//                        XLog.i(" onNext : " + bingDailyPicture);
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        XLog.e(" onError : " + throwable);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        XLog.e(" onComplete : ");
//                    }
//                });
    }

    private Map<String, String> getParams(int index) {
//        "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN
        Map<String, String> params = new HashMap<>();
        params.put("format", "js");
        params.put("idx", "0");
        params.put("n", index + "");
        params.put("mkt", "zh-CN");
        return params;
    }


}
