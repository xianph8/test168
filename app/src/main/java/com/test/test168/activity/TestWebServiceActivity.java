package com.test.test168.activity;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.test.test168.R;
import com.xian.common.module.ApiWrapper;


public class TestWebServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_service);
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

        findViewById(R.id.btn_service_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView(v);
            }
        });
        findViewById(R.id.btn_service_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView(v);
            }
        });
        findViewById(R.id.btn_service_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView(v);
            }
        });
    }

    public void onClickView(View view) {
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
