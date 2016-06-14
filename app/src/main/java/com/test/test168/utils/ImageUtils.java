package com.test.test168.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.test.test168.R;

import java.io.File;


/**
 * Created by King on 2016/4/18.
 *
 * 图片加载工具
 */
public class ImageUtils {
//    Glide.with(this)
//            .load("http://nuuneoi.com/uploads/source/playstore/cover.jpg")
//    .diskCacheStrategy(DiskCacheStrategy.ALL)
//    .into(ivImgGlide);
/*  Glide.with(context)
        .load(maybeNull)
        .placeholder(R.drawable.loading)
        .error(R.drawable.failed)
        .fallback(R.drawable.empty)
        .into(imageView)*/

    private static int loadingId = R.mipmap.git_loading;
    private static int failedId = R.mipmap.load_image_failed;

    /**
     * @param activity  建议传activity不传context , 这样可以让Glide伴着activity而加载图片
     * @param url       图片网络地址
     * @param imageView 要显示图片的控件
     */
    public static void displayUrl(Activity activity, String url, ImageView imageView) {
        if (url != null) {
            Glide.with(activity)
                    .load(url)
                    .placeholder(loadingId)
                    .error(failedId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(imageView);
            L.v("ImageUtils : " + url);
        } else {
            L.e("ImageUtils : url is null");
        }
    }

    public static void displayThumbnailUrl(Activity activity, String url, ImageView imageView) {
        if (url != null) {
            Glide.with(activity)
                    .load(url)
                    .error(failedId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            L.v("ImageUtils : " + url);
        } else {
            L.e("ImageUtils : url is null");
        }
    }

    public static void displayPath(Activity activity, String path, final ImageView imageView) {
        File file = new File(path);
        if (file.exists()) {
            Glide.with(activity)
                    .load(file)
                    .placeholder(loadingId)
                    .error(failedId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<File, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, File model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, File model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            return false;
                        }
                    })
                    .into(imageView);
            L.v("ImageUtils : " + file);
        } else {
            L.e("ImageUtils : file is not exists !");
        }
    }

    public static void displayUri(Activity activity, Uri uri, ImageView imageView) {
        if (uri != null) {
            Glide.with(activity)
                    .load(uri)
                    .placeholder(loadingId)
                    .error(failedId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            L.v("ImageUtils : " + uri);
        } else {
            L.e("ImageUtils : uri is null");
        }
    }

    public static void slideShowDisplayUrl(Context mContext, String url, ImageView imageView) {
        if (url != null) {
            Glide.with(mContext)
                    .load(R.mipmap.load_image_failed)
                    .placeholder(loadingId)
                    .error(failedId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(imageView);
            L.v("ImageUtils : " + url);
        } else {
            L.e("ImageUtils : url is null");
        }
    }

}
