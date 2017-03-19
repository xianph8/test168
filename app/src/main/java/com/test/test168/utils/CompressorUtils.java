package com.test.test168.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.xian.common.utils.XLog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by King on 2016/4/18.
 */
public class CompressorUtils {

    private static String cache_path = ""; // 存放缓存照片的路径

    /**
     * 1、
     * 通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/<package name>/files/ 目录，一般放一些长时间保存的数据
     * 通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/<package name>/cache/目录，一般存放临时缓存数据
     * <p/>
     * 2、
     * Context.getFilesDir()可以获取到"/data/data/<package name>/files" 这个方法获取的目录不是在sdcard上，而是在应用安装的目录
     * Context.getCacheDir可以获取到"/data/data/<package name>/cache" 这个方法获取的目录不是在sdcard上，而是在应用安装的目录
     * 这个文件里面的数据在设备内存不足的时候，会被系统删除数据。注意：你不能依赖系统帮你删除这些文件，当这个文件夹里面的数据超过1MB的时候，系统会删除这个文件夹里面的数据。
     */
    public static String getCompressorImage(Context mContext, String path) {
        if (mContext.getExternalCacheDir() != null) {
            cache_path = mContext.getExternalCacheDir() + "/";
        } else {
            cache_path = mContext.getCacheDir().getPath() + "/";
        }
        return compressImage(path);
    }

    /**
     * 根据路径压缩图片，并返回压缩后图片的路径
     *
     * @param srcPath
     * @return
     */
    private static String compressImage(String srcPath) {
        if (srcPath == null) {
            return srcPath;
        }
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1280f;// 这里设置高度为800f
        float ww = 720f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        String fileName = srcPath.substring(srcPath.lastIndexOf("/") + 1);
        // 压缩好比例大小后再进行质量压缩
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        return saveFile(cache_path, fileName, isBm, srcPath);
    }

    /**
     * @param path    保存文件的目录
     * @param name    保存文件的名字
     * @param is      文件
     * @param srcPath （配合compressImage方法使用）
     * @return
     */
    private static String saveFile(String path, String name, InputStream is,
                                   String srcPath) {
        String filePath;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        filePath = path + name;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            is.close();
            fos.close();
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return srcPath;
        }
    }


    public static String uriToPath(Activity context, Uri uri) {
        String uriPath = "";
        // 这里开始的部分，获取图片的路径：
        String[] proj = {MediaStore.Images.Media.DATA};
        // 好像是android多媒体数据库的封装接口，具体的看Android文档
//        Cursor cursor = context.managedQuery(uri, proj, null, null, null);
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);//stackoverflow推荐该方法
        // 按我个人理解 这个是获得用户选择的图片的索引值
        int column_index = 0;
        XLog.d("cursor : " + cursor);
        if (cursor != null) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            //将光标移至开头 ，这个很重要，不小心很容易引起越界
            cursor.moveToFirst();
            //最后根据索引值获取图片路径
            uriPath = cursor.getString(column_index);
//            cursor.close();// 貌似同一个界面第二次调的时候，会报错，这个游标已经关闭
        }
//        System.out.println("-------------------> uriPath : " + uriPath);


        // can post image
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = context.managedQuery(uri,
//                proj,                 // Which columns to return
//                null,                 // WHERE clause; which rows to return (all rows)
//                null,                 // WHERE clause selection arguments (none)
//                null);                // Order-by clause (ascending by name)
//
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        uriPath = cursor.getString(column_index);
        XLog.d("uriPath : " + uriPath);
        return uriPath;
    }

}
