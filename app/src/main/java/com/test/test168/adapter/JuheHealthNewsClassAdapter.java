package com.test.test168.adapter;

import android.content.Context;

import com.test.test168.R;
import com.test.test168.bean.JuheHealthNewsClass;

import java.util.List;

/**
 * Created by King on 2017/3/6.
 */

public abstract class JuheHealthNewsClassAdapter extends RecycleViewAdapter<JuheHealthNewsClass> {

    public JuheHealthNewsClassAdapter(Context context, List<JuheHealthNewsClass> list) {
        super(context, list);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, JuheHealthNewsClass list, int position) {
        holder.setText(R.id.tv_news_class_id, mContext.getString(R.string.health_news_class_id_, list.id));
        holder.setText(R.id.tv_news_class_name, mContext.getString(R.string.health_news_class_name_, list.name));
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_health_news_class;
    }
}
