package com.test.test168.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by King on 2016/4/9.
 * RecyclerView 的万能适配器
 */
public abstract class RecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {


    private List<T> list;

    private Context context;

    private int layoutId;

    public RecycleViewAdapter(Context context, List<T> list, int layoutId) {
        this.list = list;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onViewRecycled(final RecycleViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, final int position) {
        onBindViewHolder(holder.getViewHolder(), list, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(list, position);
            }
        });
    }

    public abstract void onBindViewHolder(ViewHolder holder, List<T> list, int position);

    public abstract void onItemClick(List<T> list, int position);

}