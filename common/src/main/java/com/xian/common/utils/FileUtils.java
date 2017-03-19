package com.xian.common.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 写文件的工具类
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

    private static final String DOWNLOAD_DIR = "download";
    private static final String CACHE_DIR = "cache";
    private static final String ICON_DIR = "icon";

    /**
     * 获取路径里的文件名，只是截字符串，有文件不存在的可能
     *
     * @param path 文件路径
     * @return 文件名
     */
    public static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    /**
     * 获取download目录
     *
     * @param mContext c
     * @return download目录
     */
    public static String getDownloadDir(Context mContext) {
        return getDir(mContext, DOWNLOAD_DIR);
    }

    /**
     * 获取cache目录
     *
     * @param mContext c
     * @return cache目录
     */
    public static String getCacheDir(Context mContext) {
        return getDir(mContext, CACHE_DIR);
    }

    /**
     * 获取icon目录
     *
     * @param mContext k
     * @return 返回icon目录
     */
    public static String getIconDir(Context mContext) {
        return getDir(mContext, ICON_DIR);
    }

    /**
     * 获取应用目录
     * 当SD卡存在时，获取SD卡上的目录，
     * 当SD卡不存在时，获取应用的cache目录
     *
     * @param mContext c
     * @param name     需要对应文件夹的名字
     * @return 目录的绝对路径，有可能返回空字符串，这时，需要检查是否有权限读写SD卡
     */
    public static String getDir(Context mContext, String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath(mContext));
        } else {
            sb.append(getCachePath(mContext));
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return "";
        }
    }

    /**
     * 判断SD卡是否挂载
     *
     * @return 是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取SD下的应用目录
     *
     * @param mContext c
     * @return 应用目录的绝对路径
     */
    public static String getExternalStoragePath(Context mContext) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator +
                "Android/data/" + mContext.getPackageName() +
                File.separator;
    }

    /**
     * 获取应用的cache目录
     *
     * @param mContext c
     * @return cache目录的绝对路径
     */
    public static String getCachePath(Context mContext) {
        File f = mContext.getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    /**
     * 创建文件夹
     *
     * @param dirPath 目标路径
     * @return 是否创建成功
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        return !(!file.exists() || !file.isDirectory()) || file.mkdirs();
    }

    /**
     * 判断文件是否可写
     *
     * @param path 文件路径
     * @return 返回是否可写
     */
    public static boolean isWriteable(String path) {
        try {
            if (ViewUtils.isNull(path)) {
                return false;
            }
            File f = new File(path);
            return f.exists() && f.canWrite();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    /**
     * 修改文件的权限,例如"777"等
     *
     * @param path 文件路径
     * @param mode 权限
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把数据写入文件
     *
     * @param is       数据流
     * @param path     文件路径
     * @param recreate 如果文件存在，是否需要删除重建
     * @return 是否写入成功
     */
    public static boolean writeFile(InputStream is, String path, boolean recreate) {
        boolean res = false;
        File f = new File(path);
        FileOutputStream fos = null;
        try {
            if (recreate && f.exists()) {
                f.delete();
            }
            if (!f.exists() && null != is) {
                File parentFile = new File(f.getParent());
                parentFile.mkdirs();
                int count = -1;
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(f);
                while ((count = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fos);
            IOUtils.close(is);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(byte[] content, String path, boolean append) {
        boolean res = false;
        File f = new File(path);
        RandomAccessFile raf = null;
        try {
            if (f.exists()) {
                if (!append) {
                    f.delete();
                    f.createNewFile();
                }
            } else {
                f.createNewFile();
            }
            if (f.canWrite()) {
                raf = new RandomAccessFile(f, "rw");
                raf.seek(raf.length());
                raf.write(content);
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(raf);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(String content, String path, boolean append) {
        return writeFile(content.getBytes(), path, append);
    }

    /**
     * 重命名
     *
     * @param src    源文件路径
     * @param des    目标文件路径
     * @param delete 是否删除源文件
     * @return 是否重命名成功
     */
    public static boolean copy(String src, String des, boolean delete) {
        File file = new File(src);
        if (!file.exists()) {
            return false;
        }
        File desFile = new File(des);
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtils.close(in);
            IOUtils.close(out);
        }
        if (delete) {
            file.delete();
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param file 需要删除的文件，文件夹
     */
    public static void deleteFile(final File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles();// 声明目录下所有的文件 files[];
                for (File file1 : files) { // 遍历目录下所有的文件
                    deleteFile(file1); // 把每个文件 用这个方法进行递归
                }
            }
            file.delete();
        } else {
            Log.e(TAG, "文件不存在！" + "\n");
        }
        Log.d(TAG, "delete file : " + file);
    }


    /**
     * 复制文件
     *
     * @param is 源文件流
     * @param os 目标文件流
     */
    public static void copy(InputStream is, OutputStream os) {
        //拷贝
        try {
            byte[] buffer = new byte[1024];
            int len = is.read(buffer);
            while (len != -1) {
                os.write(buffer, 0, len);
                len = is.read(buffer);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     *
     * @param file1 源文件
     * @param file2 目标文件
     */
    public static void copy(File file1, File file2) {
        try {
            copy(new FileInputStream(file1), new FileOutputStream(file2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     *
     * @param path1 源文件路径
     * @param path2 目标文件路径
     */
    public static void copy(String path1, String path2) {
        copy(new File(path1), new File(path2));
    }

    /**
     * 把字符串键值对的文件读入map
     *
     * @param filePath     f
     * @param defaultValue f
     * @return v
     */
    public static Map<String, String> readMap(String filePath, String defaultValue) {
        if (ViewUtils.isNull(filePath)) {
            return null;
        }
        Map<String, String> map = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                if (f.createNewFile()) {
                    Log.d(TAG, " create success ! ");
                }
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            map = new HashMap<String, String>((Map) p);// 因为properties继承了map，所以直接通过p来构造一个map
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fis);
        }
        return map;
    }

    /**
     * 把字符串键值对的map写入文件
     *
     * @param map      d
     * @param filePath d
     * @param append   d
     * @param comment  d
     */
    public static void writeMap(String filePath, Map<String, String> map, boolean append, String comment) {
        if (map == null || map.size() == 0 || ViewUtils.isNull(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            Properties p = new Properties();
            if (append) {
                fis = new FileInputStream(f);
                p.load(fis);// 先读取文件，再把键值对追加到后面
            }
            p.putAll(map);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
    }

    /**
     * 根据值读取
     *
     * @param key          d
     * @param filePath     d
     * @param defaultValue d
     * @return s
     */
    public static String readProperties(String filePath, String key, String defaultValue) {
        if (ViewUtils.isNull(key) || ViewUtils.isNull(filePath)) {
            return null;
        }
        String value = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            value = p.getProperty(key, defaultValue);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fis);
        }
        return value;
    }

    /**
     * 把键值对写入文件
     *
     * @param filePath 文件路径
     * @param key      键
     * @param value    值
     * @param comment  该键值对的注释
     */
    public static void writeProperties(String filePath, String key, String value, String comment) {
        if (ViewUtils.isNull(key) || ViewUtils.isNull(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);// 先读取文件，再把键值对追加到后面
            p.setProperty(key, value);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
    }

}