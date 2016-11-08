package com.test.test168.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;


/**
 * 功能描述：标题按钮上的弹窗（继承自PopupWindow）
 * 用 public void show(View anchor, float x, float y) 方法，可以使用listpopup显示在（x,y）
 * xy 监听控件的  public boolean onTouchMore(View view, MotionEvent motionEvent) 用 MotionEvent 即可得到
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
     * show popup in anchor right
     *
     * @param anchor show view anchor
     */
    public void show(View anchor) {
        setAnchorView(anchor);
        setHorizontalOffset(anchor != null ? anchor.getWidth() : 0);///水平
        setVerticalOffset(anchor != null ? -anchor.getHeight() / 3 * 2 : 0);///垂直
        show();
    }


    /**
     * show popup on (x, -y)
     * <p>
     * add touch listener to the anchor
     *
     * @param anchor show view anchor
     * @param x      on touch getX
     * @param y      on touch getY
     */
    public void show(View anchor, float x, float y) {
        setAnchorView(anchor);
        setHorizontalOffset((int) x);///水平
        setVerticalOffset(-(int) y);///垂直
        show();
    }
}
