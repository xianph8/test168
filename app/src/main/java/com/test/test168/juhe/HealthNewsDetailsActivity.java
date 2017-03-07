package com.test.test168.juhe;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthNewsDetailsActivity extends BaseActivity {

    @Bind(R.id.detail_toolbar)
    Toolbar detailToolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.item_detail_container)
    NestedScrollView itemDetailContainer;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_health_news_details);
        ButterKnife.bind(this);
    }

}
