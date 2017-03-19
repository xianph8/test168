package com.xian.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.xian.common.utils.XLog;


/**
 * Created by w07 on 2016/12/23 17:47
 * Description :
 */

public class CustomRecycleView extends RecyclerView {

    private int visibleThreshold = 1;

    private boolean isLoading = false;

    private OnLoadListener mOnLoadListener = null;

    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void setListener() {
        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                XLog.d("onScrolled !");
                int totalItemCount = 0, lastVisibleItem = 0;
                if (getLayoutManager() instanceof LinearLayoutManager) {
                    totalItemCount = getLayoutManager().getItemCount();
                    lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                } else if (getLayoutManager() instanceof GridLayoutManager) {
                    totalItemCount = getLayoutManager().getItemCount();
                    lastVisibleItem = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                }

                if (dy > 60)
                    loadMore(totalItemCount, lastVisibleItem);

            }
        });
    }

    private void loadMore(int totalItemCount, int lastVisibleItem) {
        XLog.d("isLoading : " + isLoading);
        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            if (mOnLoadListener != null) {
                XLog.d("load more !");
                //按顺序，否则只会 isLoading = true;
                setLoading(true);
                mOnLoadListener.onLoad();
            }
        }
    }

    public void loadComplete() {
        XLog.d("load complete !");
        setLoading(false);
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        mOnLoadListener = onLoadListener;
        setListener();
    }

    private void setLoading(boolean loading) {
        isLoading = loading;
    }

    public interface OnLoadListener {
        void onLoad();
    }

}