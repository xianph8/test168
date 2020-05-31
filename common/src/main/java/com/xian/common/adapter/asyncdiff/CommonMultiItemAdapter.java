package com.xian.common.adapter.asyncdiff;


import androidx.annotation.NonNull;
import android.view.ViewGroup;

import com.xian.common.adapter.ViewHolder;
import com.xian.common.adapter.multi.ItemViewDelegate;
import com.xian.common.adapter.multi.ItemViewDelegateManager;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonMultiItemAdapter<T> extends CommonAsyncDiffAdapter<T> {

    private ItemViewDelegateManager<T> mItemViewDelegateManager;

    public CommonMultiItemAdapter() {
        this(new ArrayList<T>());
    }

    public CommonMultiItemAdapter(List<T> datas) {
        super(datas);
        mItemViewDelegateManager = new ItemViewDelegateManager<>();
        registerItemDelegate();
    }

    //<editor-fold desc="覆写父类 RecyclerView.Adapter 方法">
    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) {
            return super.getItemViewType(position);
        } else {
            return mItemViewDelegateManager.getItemViewType(getItem(position), position);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mItemViewDelegateManager.getItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mItemViewDelegateManager.onBindItem(holder, getItem(position), holder.getAdapterPosition());
    }
    //</editor-fold>

    //<editor-fold desc="覆写父类 CommonAdapter 方法">
    // 覆写父类该方法，不用子类实现，上面把父类调用此方法的地方也覆写了，所以这个是不会被被调用的
    @Override
    protected int getItemLayoutId() {
        return 0;
    }

    @Override
    protected void onBindItem(ViewHolder holder, @NonNull T t) {
    }
    //</editor-fold>

    //<editor-fold desc="多类型 adapter 代理类相关方法">
    protected abstract void registerItemDelegate();

    public void addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
    }

    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }
    //</editor-fold>

}
