package com.test.test168.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.test.test168.utils.L;
import com.test.test168.view.dialog.LoadingSingletonDialog;

public class RefreshLayout extends SwipeRefreshLayout {

    private Context mContext;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private OnRefreshLoadListener mOnRefreshLoadListener;

    private int visibleThreshold = 1;
    private boolean isLoading = false;

    public RefreshLayout(Context context) {
        this(context, null);
        mContext = context;
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        this.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        this.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mOnRefreshLoadListener != null) {
                    mOnRefreshLoadListener.onRefresh();
                }
            }
        });

        ////  getRootView is get layout root view
//        if (this.getRootView() instanceof RecyclerView) {
//            mRecyclerView = (RecyclerView) this.getRootView();
//            mLinearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//            init();
//        }
    }

    //set the child view of RefreshLayout,RecyclerView
    public void setListView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        // 横向
       /* LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvList.setLayoutManager(layoutManager);*/

        // 纵向
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        this.mRecyclerView.setLayoutManager(layoutManager);
        //设置Item增加、移除动画
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        rvList.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        // 设置item 动画
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLinearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
//        setFootView();
        init();
        L.i("init refresh layout : ");
    }


//    // foot loading view
//    private View footView;
//    private TextView textMore;
//    private ProgressBar progressBar;
//    private int lastFootPosition = -1;
//
//    private void setFootView() {
//        if (mLinearLayoutManager != null && mRecyclerView != null) {
//            footView = View.inflate(mContext, R.layout.refresh_listview_footer, null);
//            progressBar = (ProgressBar) footView.findViewById(R.id.load_progress_bar);
//            textMore = (TextView) footView.findViewById(R.id.text_more);
//            footView.setEnabled(false);
//            progressBar.setEnabled(false);
//            textMore.setEnabled(false);
//            if (lastFootPosition != -1) {
//                mRecyclerView.removeViewAt(lastFootPosition);// remove foot view
//            }
//            lastFootPosition = mLinearLayoutManager.findLastVisibleItemPosition();
////            ((ViewGroup) footView.getParent()).removeView(footView);
//            mRecyclerView.addView(footView, lastFootPosition);// add foot view
//        }
//    }

    // init recycler view
    private void init() {
        L.i("recycler init : ");
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                L.i("recycler onScrolled : ");
                L.i("recycler onScrolled dx : " + dx);
                L.i("recycler onScrolled dy : " + dy);
                int totalItemCount = mLinearLayoutManager.getItemCount();
                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    L.i("recycler load : ");
                    if (dy > 100) {// dy > 0 scroll to bottom   else   dy < 0 scroll to top
                        if (mOnRefreshLoadListener != null) {
                            mOnRefreshLoadListener.onLoad();
                            setLoading(true);
                        }
                    }
                }
            }
        });
    }

    public void setLoading(boolean set) {
        isLoading = set;
        if (set) {
//            textMore.setText("加载中...");
//            progressBar.setVisibility(VISIBLE);
            LoadingSingletonDialog.getInstance().show(mContext, "加载中...");
        } else {
//            setFootView();
//            textMore.setText("加载更多");
//            progressBar.setVisibility(GONE);
            LoadingSingletonDialog.getInstance().dismiss();
        }
    }

    public void setOnRefreshLoadListener(OnRefreshLoadListener onRefreshLoadListener) {
        mOnRefreshLoadListener = onRefreshLoadListener;
    }

    public interface OnRefreshLoadListener {
        void onRefresh();

        void onLoad();
    }
}