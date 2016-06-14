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
public abstract class RVAdapter<T> extends RecyclerView.Adapter<RVHolder> {


    private List<T> list;

    private Context context;

    private int layoutId;

    public RVAdapter(Context context, List<T> list, int layoutId) {
        this.list = list;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        return new RVHolder(view);
    }

    @Override
    public void onViewRecycled(final RVHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(RVHolder holder, final int position) {
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