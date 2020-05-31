package com.xian.common.adapter.multi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import android.view.ViewGroup;

import com.xian.common.adapter.ViewHolder;


public class ItemViewDelegateManager<T> {
    private SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat<>();

    public int getItemViewDelegateCount() {
        return delegates.size();
    }

    //<editor-fold desc="代理类管理相关方法">

    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {
        if (delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        int indexToRemove = delegates.indexOfKey(itemType);
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }
    //</editor-fold>

    @Nullable
    public ItemViewDelegate<T> getItemViewDelegate(int viewType) {
        return delegates.get(viewType);
    }

    public int getItemViewType(ItemViewDelegate<T> itemViewDelegate) {
        return delegates.indexOfValue(itemViewDelegate);
    }

    public int getItemViewType(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    /**
     * 如果没有对应的 viewType layout 就会返回 0 ，这里需要注意一下
     *
     * @param viewType 需要的 viewType
     * @return 可能为 -1 的 layoutId
     */
    public int getItemViewLayoutId(int viewType) {
        ItemViewDelegate t = getItemViewDelegate(viewType);
        if (t == null) {
            return -1;
        }
        return t.getItemLayoutId();
    }

    public ViewHolder getItemViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = getItemViewLayoutId(viewType);
        return ViewHolder.createViewHolder(parent, layoutId);
    }

    public void onBindItem(ViewHolder holder, T item, int position) {
        ItemViewDelegate<T> delegate = getItemViewDelegate(holder.getItemViewType());
        if (delegate != null && delegate.isForViewType(item, position)) {
            delegate.onBindItem(holder, item);
        } else {
            throw new IllegalArgumentException(
                    "No ItemViewDelegateManager added that matches position=" + position + " in data source");
        }
    }

}