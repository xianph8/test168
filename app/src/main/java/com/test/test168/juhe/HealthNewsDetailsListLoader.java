package com.test.test168.juhe;

import android.content.Context;

import com.test.test168.api.JuheApi;
import com.test.test168.bean.JuheHealthNewsClass;
import com.test.test168.network.CustomJuheSub;
import com.test.test168.network.JuheApiWrapper;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by King on 2017/3/7.
 */

public class HealthNewsDetailsListLoader {

    private Context mContext;

    private int currentPage = 1;

    private String limit = "20";

    public HealthNewsDetailsListLoader(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * key	    string	是	应用APPKEY(应用详细页查询)
     * dtype	string	否	返回数据的格式,xml或json，默认json
     * limit	int	    否	每页个数
     * page	    int	    否	第几页
     * id	    int	    否	分类id
     *
     * @param newsClass
     * @param sub
     */
    public void loader(JuheHealthNewsClass newsClass, CustomJuheSub sub) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", JuheApi.key);
        map.put("dtype", JuheApi.dtype);
        map.put("limit", limit);
        map.put("page", (currentPage++) + "");
        map.put("id", newsClass.getId());
        JuheApiWrapper.getInstance()
                .create(JuheApi.class)
                .getNewsClassList(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sub);
    }

}
