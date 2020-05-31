package com.test.test168.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.test168.R;

public class TestAutoCompleteTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_auto_complete_text_view);

        //数据适配准备
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"asdfasdf", "asdf", "ertr", "hkjhk", "h4684kjhk", "897431k"});
        //单一的自动完成
        AutoCompleteTextView auto_txt = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        auto_txt.setAdapter(adapter);
    }
}
