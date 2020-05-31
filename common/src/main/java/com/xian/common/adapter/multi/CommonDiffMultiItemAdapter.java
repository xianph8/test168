package com.xian.common.adapter.multi;

import androidx.recyclerview.widget.DiffUtil;

import com.xian.common.adapter.BaseDiffCallBack;

import java.util.List;


public abstract class CommonDiffMultiItemAdapter<T> extends CommonMultiItemAdapter<T> {

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
