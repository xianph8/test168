package com.xian.common.adapter;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * 这个部分代码是抄 {@link android.support.v7.recyclerview.extensions.AsyncListDiffer}
 * 可放心食用
 *
 * @param <T>
 */
public abstract class BaseDiffCallBack<T> extends DiffUtil.Callback {

    private List<T> mNewList;
    private List<T> mOldList;

    /**
     * 构造方法
     *
     * @param newList new
     * @param oldList old
     */
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

    /**
     * 此方法用于判断是否同一个 viewType 的 item 。
     *
     * @param newItemPosition new
     * @param oldItemPosition old
     * @return result
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T newItem = mNewList.get(newItemPosition);
        T oldItem = mOldList.get(oldItemPosition);
        if (oldItem != null && newItem != null) {
            return areItemsTheSame(oldItem, newItem);
        } else {
            return oldItem == null && newItem == null;
        }
    }

    protected abstract boolean areItemsTheSame(T oldItem, T newItem);

    /**
     * 此方法用于判断上面同一个 viewType 的 item 的内容是否相同 。
     *
     * @param newItemPosition new
     * @param oldItemPosition old
     * @return result
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = mOldList.get(oldItemPosition);
        T newItem = mNewList.get(newItemPosition);
        if (oldItem != null && newItem != null) {
            return areContentsTheSame(oldItem, newItem);
        } else if (oldItem == null && newItem == null) {
            return true;
        } else {
            throw new AssertionError();
        }
    }

    protected abstract boolean areContentsTheSame(T newItem, T oldItem);
}
