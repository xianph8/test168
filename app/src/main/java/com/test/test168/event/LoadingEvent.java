package com.test.test168.event;

/**
 * Created by King on 2016/5/5.
 */
public class LoadingEvent extends BaseEventObject {

    public LoadingEvent(int event) {

        setEvent(event);

    }

    public LoadingEvent() {

    }

    /**
     * cancel :１
     * dismiss : 2
     * key :　3
     * show : 4
     */
    public static int CANCEL = 1;
    public static int DISMISS = 2;
    public static int KEY = 3;
    public static int SHOW = 4;

    private int event = 0;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }
}
