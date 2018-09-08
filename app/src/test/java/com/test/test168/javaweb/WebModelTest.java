package com.test.test168.javaweb;

import com.test.test168.netwrapper.ServerResponse;
import com.test.test168.netwrapper.SimpleObserver;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xian
 * @date 2018/6/28
 */

public class WebModelTest {

    @Test
    public void firstweb() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("username", "andevx");
        params.put("password", "test168");
        TestWebApiWrapper.getInstance().create(TestApi.class)
                .first(params)
                .subscribe(new SimpleObserver<ServerResponse<HelloAndroidBean>>() {
                    @Override
                    public void onNext(ServerResponse<HelloAndroidBean> testWebWanResponse) {
                        System.out.println("testWebWanResponse = [" + testWebWanResponse + "]");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }
                });
    }

    @Test
    public void secondweb() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("username", "andevx");
        params.put("password", "test168");
        TestWebApiWrapper.getInstance().create(TestApi.class)
                .second(params)
                .subscribe(new SimpleObserver<ServerResponse<HelloAndroidBean>>() {
                    @Override
                    public void onNext(ServerResponse<HelloAndroidBean> testWebWanResponse) {
                        System.out.println("testWebWanResponse = [" + testWebWanResponse + "]");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }
                });
    }


}
