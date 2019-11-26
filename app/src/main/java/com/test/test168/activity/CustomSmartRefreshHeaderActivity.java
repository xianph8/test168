package com.test.test168.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.test.test168.R;
import com.test.test168.adapter.TestCustomBehaviorAdapter;
import com.test.test168.view.IndexSmartRefreshHeaderLayoutView;
import com.xian.common.utils.XLog;

import java.util.ArrayList;
import java.util.List;

public class CustomSmartRefreshHeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_smart_refresh_header);

        SmartRefreshLayout smartRefreshLayout = findViewById(R.id.srl_custom_smart_refresh_header);
        IndexSmartRefreshHeaderLayoutView customAnimatorHeader = new IndexSmartRefreshHeaderLayoutView(this);
        customAnimatorHeader.setPaintColor(Color.RED);
        customAnimatorHeader.getBgImg().setImageResource(R.drawable.def_bg);
        smartRefreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        smartRefreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        smartRefreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能
        smartRefreshLayout.setHeaderMaxDragRate(1.2f);//MaxDragRate = 最大拖动距离 / Header或者Footer的高度 （要求>=1,越大阻尼越小）
        smartRefreshLayout.setFooterTriggerRate(0.4f);//HeaderTriggerRate 默认是 1，改成0.5，那么再下拉到一半的时候就可以刷新了
        smartRefreshLayout.setRefreshHeader(customAnimatorHeader);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                XLog.i(" onLoadMore : start");
                smartRefreshLayout.postDelayed(() -> {
                    XLog.i(" onLoadMore : finish");
                    refreshLayout.finishLoadMore();
                }, 5000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                XLog.i(" onRefresh : start");
                smartRefreshLayout.postDelayed(() -> {
                    XLog.i(" onRefresh : finish");
                    refreshLayout.finishRefresh();
                }, 5000);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rv_custom_smart_refresh_content);
        List<String> test1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            test1.add("test header : " + i);
        }
        recyclerView.setAdapter(new TestCustomBehaviorAdapter(test1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
