package com.test.test168.adapter;

import android.content.Context;

import com.test.test168.R;
import com.xian.common.adapter.RecycleViewAdapter;
import com.xian.common.adapter.ViewHolder;

import java.util.List;

public class TestCustomBehaviorAdapter extends RecycleViewAdapter<String> {

    public TestCustomBehaviorAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, String item) {
        holder.setText(R.id.tv_list_home_text, item);
    }

    @Override
    public void onItemClick(String item, int position) {

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_list_home;
    }
}
