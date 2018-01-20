package com.test.test168.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xian.common.R;

import java.io.File;
import java.net.URI;

/**
 * Created by w07 on 2017/5/17 14:42
 * Description :
 */

public class ImageLoader {

    public static void show(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                .into(imageView);
    }

    public static void display(Context context, String url, ImageView imageView) {
        if (url == null) url = "";
        Glide.with(context).load(url)
                .apply(getDefaultOption())
                .into(imageView);
    }

    public static void display(Context context, Uri url, ImageView imageView) {
        if (url == null) return;
        Glide.with(context).load(url)
                .apply(getDefaultOption())
                .into(imageView);
    }

    private static RequestOptions getDefaultOption() {
        return new RequestOptions()
                .centerCrop()
//                .fallback(R.drawable.img_loading) // 请求url/model为空
//                .placeholder(R.drawable.img_loading) // 请求图片加载中
//                .error(R.drawable.img_loading)
                ; // 请求图片加载错误
    }

    public static void displayRound(Context context, String url, ImageView imageView) {
        if (url == null) url = "";
        Glide.with(context).load(url)
                .apply(getDefaultOption().transform(new GlideRoundTransform(context, 3)))
                .into(imageView);
    }

    public static void displayUserHeard(Context context, String url, ImageView imageView) {
        if (url == null) url = "";
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                                .centerCrop()
//                        .placeholder(R.drawable.img_head)
//                        .error(R.drawable.img_head)
//                        .transform(new GlideCircleTransform(context))
                )
                .into(imageView);
    }

    public static void displayCover(Context context, String url, ImageView imageView) {
        if (url == null) url = "";
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                                .centerCrop()
//                        .placeholder(R.drawable.img_loading_cover)
//                        .error(R.drawable.img_loading_cover)
                )
                .into(imageView);
    }

    public static void display(Context context, Bitmap bitmap, ImageView imageView) {
        if (bitmap == null) return;
        Glide.with(context).load(bitmap).into(imageView);
    }

    public static void display(Context context, File file, ImageView imageView) {
        if (file == null) return;
        Glide.with(context).load(file).into(imageView);
    }

    public static void displayGif(Context context, int resId, ImageView imageView) {
        if (resId != 0)
            Glide.with(context)
                    .asGif()
                    .load(resId)
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
    }
}
