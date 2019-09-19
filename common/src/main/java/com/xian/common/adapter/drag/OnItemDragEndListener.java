package com.xian.common.adapter.drag;

/**
 * 用于解耦，adapter 通知 ItemTouchHelper 拖拽 item 后，拖拽完成，通知 adapter 刷新 列表
 */
public interface OnItemDragEndListener {
    boolean onItemDragEnd(int fromPosition, int toPosition);
}