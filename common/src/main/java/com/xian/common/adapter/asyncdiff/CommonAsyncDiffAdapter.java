package com.xian.common.adapter.asyncdiff;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xian.common.adapter.ViewHolder;

import java.util.List;

public abstract class CommonAsyncDiffAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    //<editor-fold desc="CommonAsyncDiffAdapter 专有的计算数据差异工具，同时持有数据列表，解决差异工具与 adapter 数据同步问题 ">
    private final AsyncDiffDataManager<T> mDataManager;
    //</editor-fold>

    public CommonAsyncDiffAdapter() {
        super();
        mDataManager = new AsyncDiffDataManager<>(this, new DiffUtil.ItemCallback<T>() {
            @Override
            public boolean areItemsTheSame(@NonNull T t, @NonNull T t1) {
                return areItemTheSame(t, t1);
            }

            @Override
            public boolean areContentsTheSame(@NonNull T t, @NonNull T t1) {
                return areContentTheSame(t, t1);
            }
        });
    }

    public CommonAsyncDiffAdapter(List<T> list) {
        this();
        mDataManager.submitList(list);
    }

    //<editor-fold desc="父类 RecyclerView.Adapter 相关实现方法">

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(parent, getItemLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T item = mDataManager.getItem(position);
        if (item != null) {
            onBindItem(holder, item);
        }
    }

    @Override
    public int getItemCount() {
        return mDataManager.getItemCount();
    }

    //</editor-fold>

    //<editor-fold desc="提供各种 items 数据列表相关操作方法 ">
    public List<T> getItems() {
        return mDataManager.getCurrentList();
    }

    public void setItems(List<T> data) {
        mDataManager.subList(data);
    }

    public void setDatas(List<T> datas) {
        setItems(datas);
    }

    public T getItem(int position) {
        return mDataManager.getItem(position);
    }

    public void addItems(List<T> data) {
        mDataManager.addItems(data);
    }

    public void addItem(T t) {
        mDataManager.addItem(t);
    }

    public void remove(T t) {
        mDataManager.remove(t);
    }

    public void remove(int index) {
        mDataManager.remove(index);
    }
    //</editor-fold>

    //<editor-fold desc="要求子类实现方法">
    protected int getItemLayoutId(int viewType) {
        return getItemLayoutId();
    }

    protected boolean areContentTheSame(T newItem, T oldItem) {
        return newItem.equals(oldItem);
    }

    protected abstract int getItemLayoutId();

    protected abstract boolean areItemTheSame(T newItem, T oldItem);

    protected abstract void onBindItem(ViewHolder holder, @NonNull T item);
    //</editor-fold>

}