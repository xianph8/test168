package com.test.test168.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.util.Log;

import java.io.File;

//跟App相关的辅助类
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序包名]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getPackageName(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(pkName, 0).versionName;
            int versionCode = context.getPackageManager().getPackageInfo(pkName, 0).versionCode;
//            return pkName + "   " + versionName + "  " + versionCode;
            return pkName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [新建一个缓存文件名]
     *
     * @return 当前应用的版本名称
     */
    public static String getCacheFileName() {
        return TimeUtils.getCurrentTimeInMillis() + ".png";
    }

    public static String getAppPath(Context mContext) {
        String appPath = Environment.getExternalStorageDirectory().getPath() + "/." + getAppName(mContext);
//        Log.i("AppUtil : ", "getAppPath : " + appPath);
        File file = new File(appPath);
        if (!file.exists()) {
            if (file.mkdir()) {
                Log.i("AppUtil : ", "app path is created !");
            }
        }
        return appPath;
    }

    public static String getAppFilePath(Context mContext) {
        String appFilePath = getAppPath(mContext) + "/file/";
        Log.i("AppUtil : ", "getAppFilePath : " + appFilePath);
        File file = new File(appFilePath);
        if (!file.exists()) {
            if (file.mkdir()) {
                Log.i("AppUtil : ", "app file path is created !");
            }
        }
        return appFilePath;
    }

    public static String getAppCachePath(Context mContext) {
        String appCachePath = getAppPath(mContext) + "/cache/";
        Log.i("AppUtil : ", "appCachePath : " + appCachePath);
        File file = new File(appCachePath);
        if (!file.exists()) {
            if (file.mkdir()) {
                Log.i("AppUtil : ", "app cache path is created !");
            }
        }
        return appCachePath;
    }

}
