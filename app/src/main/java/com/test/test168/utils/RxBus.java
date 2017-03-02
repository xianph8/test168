package com.test.test168.utils;


/*
 * Implementing an Event Bus With RxJava - RxBus
 *
 * use jar
 *
    compile 'io.reactivex:rxandroid:1.2.1'
    compile 'io.reactivex:rxjava:1.1.6'
 *
 * <p/>
 * how to use ?
 * <p/>
 * 1. send event
 * RxBus.getDefault().send(new TapEvent());
 * <p/>
 * 2. get event
    RxBus
        .getDefault()
        .toObservable()
        .subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                // to do something
            }
        });
 */
public class RxBus {

    private static volatile RxBus mDefaultInstance;

    private RxBus() {
    }

    public static RxBus getDefault() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

//    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

//    public void send(Object o) {
//        _bus.onNext(o);
//    }
//
//    public Observable<Object> toObservable() {
//        return _bus;
//    }
}
