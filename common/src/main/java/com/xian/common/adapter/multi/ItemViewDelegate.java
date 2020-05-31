package com.xian.common.adapter.multi;

import androidx.annotation.LayoutRes;

import com.xian.common.adapter.ViewHolder;


public interface ItemViewDelegate<T> {

    @LayoutRes
    int getItemLayoutId();

    boolean isForViewType(T item, int position);

    void onBindItem(ViewHolder holder, T item);

}