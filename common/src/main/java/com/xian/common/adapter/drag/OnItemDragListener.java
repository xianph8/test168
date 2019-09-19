package com.xian.common.adapter.drag;

/**
 * 监听拖拽的全过程
 */
public interface OnItemDragListener {
    void onDragStart();

    boolean onDragging(int fromPosition, int toPosition);

    void onDragEnd();
}