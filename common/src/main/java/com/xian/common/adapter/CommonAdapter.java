package com.xian.common.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    //<editor-fold desc="CommonAdapter 持有的数据成员变量">
    protected List<T> items = new ArrayList<>();
    //</editor-fold>

    public CommonAdapter() {
        super();
    }

    public CommonAdapter(List<T> list) {
        this();
        if (list != null)
            items.addAll(list);
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
        T item = getItem(position);
        if (item != null) {
            onBindItem(holder, item);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }
    //</editor-fold>

    //<editor-fold desc=" items 数据列表相关操作方法 ">

    public T getItem(int position) {
        if (position > items.size() || position < 0) {
            return null;
        }
        return items.get(position);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> data) {
        this.items.clear();
        addItems(data);
    }

    public void addItems(List<T> data) {
        if (data != null)
            this.items.addAll(data);
        this.notifyDataSetChanged();
    }

    public void addItem(T t) {
        if (items != null) {
            items.add(t);
            notifyItemInserted(items.size());
        }
    }

    public void remove(T t) {
        if (items != null) {
            int index = items.indexOf(t);
            if (index != -1) {
                items.remove(t);
                notifyItemRemoved(index);
            }
        }
    }

    public void remove(int index) {
        if (items != null && index >= 0 && index < items.size()) {
            items.remove(index);
            notifyItemRemoved(index);
        }
    }
    //</editor-fold>

    //<editor-fold desc="要求子类实现方法">

    protected abstract int getItemLayoutId();

    protected int getItemLayoutId(int viewType) {
        return getItemLayoutId();
    }

    protected abstract void onBindItem(ViewHolder holder, @NonNull T item);
    //</editor-fold>

}