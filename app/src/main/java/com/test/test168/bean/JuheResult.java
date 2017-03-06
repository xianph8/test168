package com.test.test168.bean;

/**
 * Created by King on 2017/3/6.
 */

public class JuheResult<T> {

    /**
     * "reason": "成功的返回",
     * "result":{}
     * "error_code": 0
     */

    public int error_code;  //int	返回码
    public String reason;//string	返回说明
    public T result;//string	返回结果集

}
