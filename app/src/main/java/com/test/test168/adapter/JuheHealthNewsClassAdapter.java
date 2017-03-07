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
//        holder.setText(R.id.tv_news_class_description, mContext.getString(R.string.health_news_class_description_, list.getDescription() ));
//        holder.setText(R.id.tv_news_class_title, mContext.getString(R.string.health_news_class_title_, list.getTitle() ));
//        holder.setText(R.id.tv_news_class_name, mContext.getString(R.string.health_news_class_name_, list.getName()));
        holder.setText(R.id.tv_news_class_description, list.getDescription());
        holder.setText(R.id.tv_news_class_title, list.getTitle());
        holder.setText(R.id.tv_news_class_name, list.getName());
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_health_news_class;
    }
}
