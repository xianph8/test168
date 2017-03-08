package com.test.test168.bean;

/**
 * Created by King on 2017/3/7.
 */

public class JuheResultBean<T> {

    public int total;

    public ListBean<T> list;

    @Override
    public String toString() {
        return "JuheResultBean{" +
                "list=" + list +
                '}';
    }
}
