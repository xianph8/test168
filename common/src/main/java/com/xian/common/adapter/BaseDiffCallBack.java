package com.xian.common.adapter;

import android.support.v7.util.DiffUtil;

import java.util.List;

public abstract class BaseDiffCallBack<T> extends DiffUtil.Callback {

    private List<T> mNewList;
    private List<T> mOldList;

    public BaseDiffCallBack(List<T> newList, List<T> oldList) {
        this.mNewList = newList;
        this.mOldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T newItem = mNewList.get(newItemPosition);
        T oldItem = mOldList.get(oldItemPosition);
        if (newItem == null || oldItem == null) return false;
        return areItemsTheSame(newItem, oldItem);
    }

    protected abstract boolean areItemsTheSame(T newItem, T oldItem);

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T newItem = mNewList.get(newItemPosition);
        T oldItem = mOldList.get(oldItemPosition);
        if (newItem == null || oldItem == null) return false;
        return areContentsTheSame(newItem, oldItem);
    }

    protected abstract boolean areContentsTheSame(T newItem, T oldItem);
}
