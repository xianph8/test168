package com.test.test168.api;


import com.test.test168.bean.BingDailyPicture;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by King on 2017/3/18.
 */

public interface BingApi {


    @POST("/HPImageArchive.aspx")
    @FormUrlEncoded
    Observable<BingDailyPicture> loadDailyPicture(@FieldMap Map<String, String> params);

}
