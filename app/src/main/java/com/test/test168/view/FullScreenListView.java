package com.test.test168.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by XianPH on 2015/8/4.
 */
public class FullScreenListView extends ListView {

    public FullScreenListView(Context context) {
        super(context);
    }

    public FullScreenListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}