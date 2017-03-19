package com.xian.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by w07 on 2016/12/23 18:30
 * Description :
 */
public class CustomHorizontalScrollView extends HorizontalScrollView {
    private GestureDetector mGestureDetector;

    /**
     * @param context Interface to global information about an application environment.
     * @function CustomHScrollView constructor
     */
    public CustomHorizontalScrollView(Context context) {
        super(context);
        mGestureDetector = new GestureDetector(new HScrollDetector());
        setFadingEdgeLength(0);
    }

    /**
     * @param context Interface to global information about an application environment.
     * @param attrs   A collection of attributes, as found associated with a tag in an XML document.
     * @function CustomHScrollView constructor
     */
    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new HScrollDetector());
        setFadingEdgeLength(0);
    }

    /**
     * @param context  Interface to global information about an application environment.
     * @param attrs    A collection of attributes, as found associated with a tag in an XML document.
     * @param defStyle style of view
     * @function CustomHScrollView constructor
     */
    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mGestureDetector = new GestureDetector(new HScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    } // Return false if we're scrolling in the y direction

    class HScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX) > Math.abs(distanceY);
        }
    }
}