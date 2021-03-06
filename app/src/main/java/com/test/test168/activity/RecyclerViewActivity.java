package com.test.test168.activity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.bean.DummyContent;
import com.test.test168.fragment.RecycleFragment;
import com.xian.common.utils.XLog;

public class RecyclerViewActivity extends BaseActivity implements RecycleFragment.OnListFragmentInteractionListener {

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include_head_toolbar);
        setSupportActionBar(toolbar);

        // init fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_recycler, RecycleFragment.newInstance(2), RecycleFragment.class.getName())
                .commit();

        FloatingActionButton fab = $(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        XLog.i("onListFragmentInteraction  : " + item.toString());
    }
}
