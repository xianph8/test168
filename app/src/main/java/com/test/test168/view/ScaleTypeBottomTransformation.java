package com.test.test168.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.xian.common.utils.XLog;

import java.security.MessageDigest;


/**
 * 图片居中，并且居底显示，抄 glide 源码的{@link CenterCrop}
 */
public class ScaleTypeBottomTransformation extends BitmapTransformation {
    private static final String ID = "com.bumptech.glide.load.resource.bitmap.ScaleTypeBottomTransformation";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private void applyMatrix(@NonNull Bitmap inBitmap, @NonNull Bitmap targetBitmap, Matrix matrix) {
        int PAINT_FLAGS = Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG;
        final Paint DEFAULT_PAINT = new Paint(PAINT_FLAGS);
        Canvas canvas = new Canvas(targetBitmap);
        canvas.drawBitmap(inBitmap, matrix, DEFAULT_PAINT);
        canvas.setBitmap(null);
    }

    private Bitmap.Config getSafeConfig(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    @Override
    public Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap inBitmap, int width, int height) {
        XLog.i("pool        = " + pool);
        XLog.i("toTransform = " + inBitmap);
        XLog.i("toTransform = " + inBitmap.getWidth());
        XLog.i("toTransform = " + inBitmap.getHeight());
        XLog.i("outWidth    = " + width);
        XLog.i("outHeight   = " + height);
        if (inBitmap.getWidth() == width && inBitmap.getHeight() == height) {
            return inBitmap;
        }
        // From ImageView/Bitmap.createScaledBitmap.
        final float scale;
        final float dx;
        final float dy;
        Matrix m = new Matrix();
        if (inBitmap.getWidth() * height > width * inBitmap.getHeight()) {
            scale = (float) height / (float) inBitmap.getHeight();
            dx = (width - inBitmap.getWidth() * scale) * 0.5f;
            dy = 0;
        } else {
            scale = (float) width / (float) inBitmap.getWidth();
            dx = 0;
            dy = Math.abs((height - inBitmap.getHeight() * scale));
        }
        m.setScale(scale, scale);
        m.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
        Bitmap result = pool.get(width, height, getSafeConfig(inBitmap));
        // We don't add or remove alpha, so keep the alpha setting of the Bitmap we were given.
        TransformationUtils.setAlpha(inBitmap, result);
        applyMatrix(inBitmap, result, m);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CenterCrop;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}