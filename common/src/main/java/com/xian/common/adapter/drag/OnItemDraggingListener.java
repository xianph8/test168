package com.xian.common.adapter.drag;

import android.support.v7.widget.RecyclerView;

/**
 * 用于解耦， adapter 告诉 ItemTouchHelper 可以执行 拖拽 动画，不断拖拽，不断回调
 */
public interface OnItemDraggingListener {
    void onDragging(RecyclerView.ViewHolder viewHolder);
}