package com.xian.common.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by King on 2016/4/9.
 * RecycleView 的 ViewHolder
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {

    private ViewHolder viewHolder;

    public RecycleViewHolder(View itemView) {
        super(itemView);
        viewHolder = ViewHolder.getViewHolder(itemView);
    }

    public ViewHolder getViewHolder() {
        return viewHolder;
    }

}