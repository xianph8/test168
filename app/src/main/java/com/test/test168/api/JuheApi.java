package com.test.test168.api;

import com.test.test168.bean.JuheHealthNewsClass;
import com.test.test168.bean.JuheHealthNewsClassList;
import com.test.test168.bean.JuheHealthNewsClassListItem;
import com.test.test168.bean.JuheHealthNewsDetails;
import com.test.test168.bean.JuheResult;
import com.test.test168.bean.JuheResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by King on 2017/3/6.
 */

public interface JuheApi {

    public static final String key = "b8fda876f30aa11099cfde5c698377f4";
    public static final String dtype = "json";

    @POST("/yi18/news/newsclass")
    @FormUrlEncoded
    Observable<JuheResult<JuheResultBean<JuheHealthNewsClass>>> getNewsClass(@FieldMap Map<String, String> params);

    @POST("/yi18/news/list")
    @FormUrlEncoded
    Observable<JuheResult<JuheHealthNewsClassList<JuheHealthNewsClassListItem>>> getNewsClassList(@FieldMap Map<String, String> params);

    @POST("/yi18/news/show")
    @FormUrlEncoded
    Observable<JuheResult<JuheHealthNewsDetails>> getNewsDetails(@FieldMap Map<String, String> params);

}
