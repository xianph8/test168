package com.test.test168.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by King on 2017/3/7.
 */
public class ListBean<T> {
    /**
     * status : true
     * tngou : [{},{},{},{},{},{},{}]
     */

    public boolean status;

    @SerializedName("tngou")
    public List<T> tList;

    @Override
    public String toString() {
        return "ListBean{" +
                "status=" + status +
                ", tList=" + tList +
                '}';
    }
}