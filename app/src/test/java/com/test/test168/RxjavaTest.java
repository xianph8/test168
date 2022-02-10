package com.test.test168;

import android.util.Base64;
import android.util.Log;

import com.xian.common.utils.XLog;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;

/**
 * Created by xian on 2017/8/19.
 */

public class RxjavaTest {

    private static final String TAG = "RxjavaTest";

    @Test
    public void test1() throws Exception {
        Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return null;
            }
        });

        // show loading dialog
        // start timer
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        // timer over
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // dismiss dialog
                    }
                });

    }



    public void testName() {

//        516834cc-50e448af
        /*byte[] decode = Base64.decode(
                new byte[]{
                        78, 106, 73, 49, 79, 122, 65, 51,
                        89, 71, 65, 117, 78, 106, 78, 109,
                        78, 122, 99, 55, 89, 109, 85, 61
                }, android.util.Base64.NO_WRAP
        );*/
//        bcf9ed53-9ae4328e
        byte[] decode = Base64.decode(
                new byte[]{
                        89, 87, 66, 108, 79, 109, 90, 110, 78, 106, 65, 117, 79, 109, 74, 109, 78, 122, 65, 120, 79, 50, 89, 61
                }, android.util.Base64.NO_WRAP
        );
        System.out.println("decode = " + Arrays.toString(decode));
        Log.d(TAG, "testName: "+new String(decode));
        int length = decode.length;
        int flag = 3;
        for (int i = 0; i < length; i++) {
            decode[i] = (byte) (decode[i] ^ flag);
        }
        System.out.println("decode = " + Arrays.toString(decode));
        Log.d(TAG, "testName: "+new String(decode));

    }


    public void testBiliBili(){

        String command = "e9ca6f21583a1533d3ff4fd47ddc463c6a1c7d2cf084d364";
//        byte[] bytes = HexUtils.hexStringToBytes(command);
//        XLog.d(" result : " +new String(bytes));

    }


}
