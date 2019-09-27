package com.xian.common.adapter.asyncdiff;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 为保证数据统一性，有必要做一个数据管理的代码类
 * 这个是异步数据差异处理管理器，可以随便加一些统一的数据处理方法
 *
 * @param <T>
 */
public class AsyncDiffDataManager<T> extends AsyncListDiffer<T> {

    public AsyncDiffDataManager(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(adapter, diffCallback);
    }

    public AsyncDiffDataManager(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> config) {
        super(listUpdateCallback, config);
    }

    public List<T> getItems() {
        return getCurrentList();
    }

    private List<T> getModifiableList() {
        return new ArrayList<>(getItems());
    }

    public void subList(List<T> data) {
        submitList(data);
    }

    public int getItemCount() {
        if (getItems() == null) return 0;
        return getItems().size();
    }

    @Nullable
    public T getItem(int position) {
        if (position > getItems().size() || position < 0) {
            return null;
        }
        return getItems().get(position);
    }

    public void addItems(List<T> data) {
        if (data != null) {
            List<T> list = getModifiableList();
            list.addAll(data);
            subList(list);
        }
    }

    public void addItem(T t) {
        if (isNotNullList()) {
            List<T> list = getModifiableList();
            list.add(t);
            subList(list);
        }
    }

    public void remove(T t) {
        if (isNotNullList()) {
            List<T> list = getModifiableList();
            int index = list.indexOf(t);
            if (index != -1) {
                list.remove(t);
                subList(list);
            }
        }
    }

    public boolean isNotNullList() {
        return getItems() != null;
    }

    public void remove(int index) {
        if (isNotNullList() && index >= 0 && index < getItems().size()) {
            List<T> list = getModifiableList();
            list.remove(index);
            subList(list);
        }
    }
}
