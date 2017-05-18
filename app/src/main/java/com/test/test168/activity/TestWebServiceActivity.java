package com.test.test168.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.test.test168.R;
import com.xian.common.module.ApiWrapper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestWebServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_service);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Logger.d("Action");

    }

    @OnClick({R.id.btn_service_test1, R.id.btn_service_test2, R.id.btn_service_test3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_service_test1:
                ApiWrapper.getInstance();
                break;
            case R.id.btn_service_test2:
                break;
            case R.id.btn_service_test3:
                break;
        }
    }
}
