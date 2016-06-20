package com.test.test168.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by King on 2016/4/9.
 * RecycleView 的 ViewHolder
 */
public class RVHolder extends RecyclerView.ViewHolder {

    private ViewHolder viewHolder;

    public RVHolder(View itemView) {
        super(itemView);
        viewHolder = ViewHolder.getViewHolder(itemView);
    }


    public ViewHolder getViewHolder() {
        return viewHolder;
    }

}