package com.test.test168.arch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.test168.R;

public class TestBaseViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_base_view_model_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestBaseViewModelFragment.newInstance())
                    .commitNow();
        }
    }
}
