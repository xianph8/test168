package com.test.test168.bean;

import java.util.List;

/**
 * Created by King on 2017/3/8.
 */

public class JuheHealthNewsClassList<T> {

    public int total;

    public List<T> list;

    @Override
    public String toString() {
        return "JuheHealthNewsClassList{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
