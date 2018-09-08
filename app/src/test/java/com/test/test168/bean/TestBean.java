package com.test.test168.bean;


import com.test.test168.netwrapper.ServerResponse;

/**
 * @author xian
 * @date 2018/6/25
 */

public class TestBean extends ServerResponse<TestBean> {

    public int a = 1;
    public int b = 2;

    @Override
    public String toString() {
        return "TestBean{" +
                "a=" + a +
                ", b=" + b +
                '}' + super.toString();
    }
}
