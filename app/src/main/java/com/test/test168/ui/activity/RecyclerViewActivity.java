package com.test.test168.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.ui.test.DummyContent;
import com.test.test168.ui.fragment.RecycleFragment;
import com.test.test168.utils.L;

public class RecyclerViewActivity extends BaseActivity implements RecycleFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        L.i("onListFragmentInteraction  : " + item.toString());
    }
}
