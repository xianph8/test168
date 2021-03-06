package com.xian.common.widget;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;

import com.xian.common.utils.XLog;


/**
 * @author ddh
 *         功能描述：标题按钮上的弹窗（继承自PopupWindow）
 */
public class ListPopup extends ListPopupWindow {

    public ListPopup(@NonNull Context context) {
        super(context);
    }

    public ListPopup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ListPopup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListPopup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * show popup on (x,-y)
     *
     * @param anchor show view anchor
     */
    public void show(View anchor, MotionEvent motionEvent) {
        show(anchor, motionEvent.getX(), motionEvent.getY());
    }

    /**
     * show popup on (x,-y)
     *
     * @param anchor show view anchor
     */
    public void show(View anchor, float x, float y) {
        setAnchorView(anchor);
        setHorizontalOffset((int) x);///水平
//        setVerticalOffset((int) y - anchor.getHeight());///垂直
        setVerticalOffset(-(int) y);///垂直
        show();
    }

    public void show(View anchor) {
        setAnchorView(anchor);
        show();
    }

    public void showAnchorViewRightTop(View anchor) {
        setAnchorView(anchor);
        setHorizontalOffset(anchor.getWidth() + 10);///水平
        show();
    }

    public void setXY(MotionEvent motionEvent) {
        setHorizontalOffset((int) motionEvent.getX());///水平
//        setVerticalOffset((int) y - anchor.getHeight());///垂直
        setVerticalOffset(-(int) motionEvent.getY());///垂直
    }

    /**
     * 该方法，已经计算了adapter的每个子项，取其中最大的宽度
     *
     * @param listAdapter 设置好的适配器
     */
    public void setAdapter(@Nullable ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        int width = 0;/////宽度
        int padding = 10;/////左右内边距
        if (listAdapter != null) {
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, this.getListView());
                listItem.measure(0, 0);
                width = listItem.getMeasuredWidth() > width ? listItem.getMeasuredWidth() : width;
                XLog.i("width : " + width);
            }
        }
        this.setWidth(width + padding);///////添加一个内边距
    }

}
