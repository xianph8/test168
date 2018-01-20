package com.test.test168.netwrapper;

/**
 * API返回结果的统一格式类(需要与后台确定好)
 */
public class ServerResponse<T> {

    /**
     * 1是成功，0失败
     * 400 异常(参数/时间/签名/请求等异常)
     * 401 异常(没有权限/未登录)
     */
    public int state;

    /**
     * 错误代码或提示信息
     */
    public String msg;

    /**
     * 返回的数据
     */
    public T data;

    @Override
    public String toString() {
        return "ServerResponse{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return state == 1;
    }
}
