package com.test.test168.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.test.test168.R;
import com.test.test168.utils.ImageLoader;
import com.xian.common.adapter.RecycleViewAdapter;
import com.xian.common.adapter.ViewHolder;

import java.util.List;

/**
 * Created by Test on 2017/1/7.
 */

public abstract class ShareToWeChatAdapter extends RecycleViewAdapter<Uri> {

    public ShareToWeChatAdapter(Context context, List<Uri> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Uri item) {

        ImageView content = holder.getView(R.id.iv_item_share_to_wechat);
        ImageLoader.display(content.getContext(), item, content);

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_share_to_wechat_picture;
    }
}
