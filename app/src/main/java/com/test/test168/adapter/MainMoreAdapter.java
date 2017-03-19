package com.test.test168.adapter;

import android.content.Context;

import com.xian.common.adapter.CommonAdapter;
import com.xian.common.adapter.ViewHolder;

import java.util.Arrays;

/**
 * Created by w07 on 2016/11/8 10:32
 * Usage : example
 */

public class MainMoreAdapter extends CommonAdapter<String> {

    public MainMoreAdapter(Context context, String[] mData) {
        super(context, Arrays.asList(mData));
    }

    @Override
    public void convert(ViewHolder holder, String item, int position) {
//        holder.setText(R.id.tv_content, item);
    }

    @Override
    public int getItemLayoutId() {
        return 0;
    }
}
