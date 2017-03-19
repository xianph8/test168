package com.test.test168.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.bean.ItemHomeMenu;
import com.xian.common.adapter.RecycleViewAdapter;
import com.xian.common.adapter.ViewHolder;

import java.util.List;

/**
 * Created by Test on 2017/1/7.
 */

public abstract class HomeMenuAdapter extends RecycleViewAdapter<ItemHomeMenu> {

    public HomeMenuAdapter(Context context, List<ItemHomeMenu> list) {
        super(context, list);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, ItemHomeMenu item) {

        TextView content = holder.getView(R.id.item_home_content);
        content.setText(item.getMenuName());
        Drawable drawable = item.getMenuIcon();
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            content.setCompoundDrawables(null, drawable, null, null);
        }

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_home_menu;
    }
}
