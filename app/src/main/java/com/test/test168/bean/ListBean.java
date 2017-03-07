package com.test.test168.bean;

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
    public List<T> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<T> getTngou() {
        return tngou;
    }

    public void setTngou(List<T> tngou) {
        this.tngou = tngou;
    }

    @Override
    public String toString() {
        return "ListBean{" +
                "status=" + status +
                ", tngou=" + tngou +
                '}';
    }
}