package com.xian.common.adapter;

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

    List<T> mList;

    private Context mContext;

    public RecycleViewAdapter(Context context, List<T> list) {
        this.mList = list;
        this.mContext = context;
    }

    /**
     * true ：并且root存在，将xml挂载到root下，返回root
     * false：返回xml的跟布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getItemLayoutId(), parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onViewRecycled(final RecycleViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, final int position) {
        onBindViewHolder(holder.getViewHolder(), mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(mList.get(position), position);
            }
        });
    }

    public abstract void onBindViewHolder(ViewHolder holder, T list);

    public abstract void onItemClick(T list, int position);

    public abstract int getItemLayoutId();


}