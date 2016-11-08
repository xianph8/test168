package com.test.test168.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/7/29 0029 10:59
 * Usage : ListView GridView 的万能适配器
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;

    public CommonAdapter(Context context, List<T> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item, int position);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, getItemLayoutId(), position);
    }

    public abstract int getItemLayoutId();
}
