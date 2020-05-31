package com.xian.common.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public abstract class CommonDiffAdapter<T> extends CommonAdapter<T> {

    public void setDiffList(List<T> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CommonDiff(newList, items));
        items.clear();
        this.items.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    protected abstract boolean areItemSame(T newItem, T oldItem);

    public class CommonDiff extends BaseDiffCallBack<T> {

        public CommonDiff(List<T> newList, List<T> oldList) {
            super(newList, items);
        }

        @Override
        protected boolean areItemsTheSame(T newItem, T oldItem) {
            return areItemSame(newItem, oldItem);
        }

        @Override
        protected boolean areContentsTheSame(T newItem, T oldItem) {
            return newItem.equals(oldItem);
        }
    }

}
