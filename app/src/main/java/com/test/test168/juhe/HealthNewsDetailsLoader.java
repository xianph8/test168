package com.test.test168.juhe;

import android.content.Context;

import com.test.test168.api.JuheApi;
import com.test.test168.bean.JuheHealthNewsClassListItem;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by King on 2017/3/7.
 */

public class HealthNewsDetailsLoader {

    private Context mContext;

    public HealthNewsDetailsLoader(Context mContext) {
        this.mContext = mContext;
    }

    public void loader(JuheHealthNewsClassListItem listItem, CustomJuheSub sub) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", JuheApi.key);
        map.put("dtype", JuheApi.dtype);
        map.put("id", listItem.getId());
        JuheApiWrapper.getInstance()
                .create(JuheApi.class)
                .getNewsDetails(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sub);
    }

}
