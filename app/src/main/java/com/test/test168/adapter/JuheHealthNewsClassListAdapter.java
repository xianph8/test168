package com.test.test168.adapter;

import android.content.Context;

import com.test.test168.R;
import com.test.test168.bean.JuheHealthNewsClassListItem;
import com.xian.common.adapter.RecycleViewAdapter;
import com.xian.common.adapter.ViewHolder;

import java.util.List;

/**
 * Created by King on 2017/3/6.
 */

public abstract class JuheHealthNewsClassListAdapter extends RecycleViewAdapter<JuheHealthNewsClassListItem> {

    public JuheHealthNewsClassListAdapter(Context context, List<JuheHealthNewsClassListItem> list) {
        super(context, list);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, JuheHealthNewsClassListItem list) {
//        holder.setText(R.id.tv_news_class_list_description, mContext.getString(R.string.health_news_class_list_description_, list.getDescription() ));
//        holder.setText(R.id.tv_news_class_list_title, mContext.getString(R.string.health_news_class_list_title_, list.getTitle() ));
//        holder.setText(R.id.tv_news_class_list_name, mContext.getString(R.string.health_news_class_list_name_, list.getName()));
        holder.setText(R.id.tv_news_class_list_description, list.getDescription());
        holder.setText(R.id.tv_news_class_list_title, list.getTitle());
        holder.setText(R.id.tv_news_class_list_time, new java.sql.Date(list.getTime()).toString());
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_health_news_class_list;
    }
}
