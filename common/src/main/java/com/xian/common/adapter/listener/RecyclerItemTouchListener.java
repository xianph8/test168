package com.xian.common.adapter.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class RecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector mGestureDetector;

    public RecyclerItemTouchListener(final RecyclerView recyclerView) {

        mGestureDetector = new GestureDetector(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            // 单击
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    onItemClick(childView, recyclerView.getChildLayoutPosition(childView));
                    return true;
                }
                return false;
            }

            // 双击
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    onItemDoubleClick(childView, recyclerView.getChildLayoutPosition(childView));
                    return true;
                }
                return false;
            }

            // 长按
            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    onItemLongClick(childView, recyclerView.getChildLayoutPosition(childView));
                }
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);// 把事件交给 mGestureDetector 处理
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClick(View itemView, int itemPosition);

    public abstract void onItemDoubleClick(View itemView, int itemPosition);

    public abstract void onItemLongClick(View itemView, int itemPosition);

}
