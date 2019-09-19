package com.xian.common.adapter.listener;

import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemViewClickListener implements RecyclerView.OnItemTouchListener {

    public static final int ITEM_VIEW_ID = 0;

    private GestureDetector mGestureDetector;

    private SparseArrayCompat<OnRecyclerViewItemClickListener> idListeners = new SparseArrayCompat<>();

    public RecyclerItemViewClickListener() {
    }

    public RecyclerItemViewClickListener(RecyclerView recyclerView) {
        initGestureDetector(recyclerView);
    }

    public RecyclerItemViewClickListener(RecyclerView recyclerView, OnRecyclerViewItemClickListener listener) {
        idListeners.put(ITEM_VIEW_ID, listener);
        initGestureDetector(recyclerView);
    }

    private void initGestureDetector(final RecyclerView recyclerView) {
        mGestureDetector = new GestureDetector(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            // 单击
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    int childViewPosition = recyclerView.getChildLayoutPosition(childView);
                    // 检查是否点击中了监听的 view
                    for (int i = 0; i < idListeners.size(); i++) {
                        int listenerId = idListeners.keyAt(i);
                        View clickView = childView.findViewById(listenerId);
                        if (clickView != null && isInView(clickView, e.getX(), e.getY())) {
                            idListeners.valueAt(i).onItemClick(clickView, childViewPosition);
                            return true;
                        }
                    }
                    // 上面如果没有 view 被点中，那就是点中了整个 itemView
                    if (idListeners.containsKey(ITEM_VIEW_ID)) {
                        idListeners.valueAt(idListeners.indexOfKey(ITEM_VIEW_ID)).onItemClick(childView, childViewPosition);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void addDefaultItemListener(OnRecyclerViewItemClickListener listener) {
        idListeners.put(ITEM_VIEW_ID, listener);
    }

    public void addListener(int id, OnRecyclerViewItemClickListener listener) {
        idListeners.put(id, listener);
    }

    /**
     * 计算指定的 View 在屏幕中的坐标。
     */
    private RectF calcViewScreenLocation(View view) {
        int[] location = new int[2];
        // 获取控件在屏幕中的位置，返回的数组分别为控件左顶点的 x、y 的值
        view.getLocationOnScreen(location);
        return new RectF(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
    }

    public boolean isInView(View v, float touchX, float touchY) {
        RectF f = calcViewScreenLocation(v);
        return f.contains(touchX, touchY);
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

}
