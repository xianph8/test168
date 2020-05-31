package com.xian.common.adapter.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * 该类简单的实现了 recycler view 的 item 单击事件
 */
public abstract class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (childView != null) {
                onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                return true;
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClick(View itemView, int itemPosition);

}
