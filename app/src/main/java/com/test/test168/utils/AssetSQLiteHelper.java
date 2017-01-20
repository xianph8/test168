package com.test.test168.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by w07 on 2017/1/20 11:21
 * Description : 用于从 asset 复制 升级 数据库的工具类
 * Author : http://stormzhang.com/android/sqlite/2013/09/02/android-sqliteassethelper/
 * 使用方法 :
 * <p>
 * 使用时只需把实现准备好的sqlite文件压缩成zip包放在assets文件夹下的databases目录，
 * 然后定义一个Helper继承自SqliteAssetHelper,如下代码：
 * <code>
 *  public class DBHelper extends SQLiteAssetHelper {
 *      private static final String DATABASE_NAME = "bhdb.sqlite";
 *      private static final int DATABASE_VERSION = 1;
 *      public DBHelper(Context context) {
 *          super(context, DATABASE_NAME, null, DATABASE_VERSION);
 *      }
 *  }
 * </code>
 * <p>
 * 升级的时候只需要改DATABASE_VERSION的值就好了。
 */

public class AssetSQLiteHelper extends SQLiteOpenHelper {
    static final String TAG = AssetSQLiteHelper.class.getSimpleName();
    private static final String ASSET_DB_PATH = "databases";

    private final Context mContext;
    private final String mName;
    private final CursorFactory mFactory;
    private final int mNewVersion;

    private SQLiteDatabase mDatabase = null;
    private boolean mIsInitializing = false;

    private String mDatabasePath;
    private String mArchivePath;

    public AssetSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

        if (version < 1) throw new IllegalArgumentException("Version must be >= 1, was " + version);
        if (name == null) throw new IllegalArgumentException("Databse name cannot be null");

        mContext = context;
        mName = name;
        mFactory = factory;
        mNewVersion = version;

        mArchivePath = ASSET_DB_PATH + "/" + name + ".zip";
        mDatabasePath = context.getApplicationInfo().dataDir + "/databases";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // do nothing - createOrOpenDatabase() is called in
        // getWritableDatabase() to handle database creation.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        if (mDatabase != null && mDatabase.isOpen() && !mDatabase.isReadOnly()) {
            return mDatabase;  // The database is already open for business
        }

        if (mIsInitializing) {
            throw new IllegalStateException("getWritableDatabase called recursively");
        }

        // If we have a read-only database open, someone could be using it
        // (though they shouldn't), which would cause a lock to be held on
        // the file, and our attempts to open the database read-write would
        // fail waiting for the file lock.  To prevent that, we acquire the
        // lock on the read-only database, which shuts out other users.

        boolean success = false;
        SQLiteDatabase db = null;
        //if (mDatabase != null) mDatabase.lock();
        try {
            mIsInitializing = true;
            db = createOrOpenDatabase(false);

            int version = db.getVersion();
            Log.e(TAG, "old version:" + version);
            Log.e(TAG, "new version:" + mNewVersion);

            // do force upgrade
            if (version != 0 && version < mNewVersion) {
                db = createOrOpenDatabase(true);
                version = db.getVersion();
            }

            onOpen(db);
            success = true;
            return db;
        } catch (SQLiteAssetException e) {
            e.printStackTrace();
        } finally {
            mIsInitializing = false;
            if (success) {
                if (mDatabase != null) {
                    try {
                        mDatabase.close();
                    } catch (Exception e) {
                    }
                    //mDatabase.unlock();
                }
                mDatabase = db;
            } else {
                //if (mDatabase != null) mDatabase.unlock();
                if (db != null) db.close();
            }
        }
        return db;
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        if (mDatabase != null && mDatabase.isOpen()) {
            return mDatabase;  // The database is already open for business
        }

        if (mIsInitializing) {
            throw new IllegalStateException("getReadableDatabase called recursively");
        }

        try {
            return getWritableDatabase();
        } catch (SQLiteException e) {
            if (mName == null) throw e;  // Can't open a temp database read-only!
            Log.e(TAG, "Couldn't open " + mName + " for writing (will try read-only):", e);
        }

        SQLiteDatabase db = null;
        try {
            mIsInitializing = true;
            String path = mContext.getDatabasePath(mName).getPath();
            db = SQLiteDatabase.openDatabase(path, mFactory, SQLiteDatabase.OPEN_READONLY);
            if (db.getVersion() != mNewVersion) {
                throw new SQLiteException("Can't upgrade read-only database from version " +
                        db.getVersion() + " to " + mNewVersion + ": " + path);
            }

            onOpen(db);
            Log.w(TAG, "Opened " + mName + " in read-only mode");
            mDatabase = db;
            return mDatabase;
        } finally {
            mIsInitializing = false;
            if (db != null && db != mDatabase) db.close();
        }
    }

    private SQLiteDatabase createOrOpenDatabase(boolean force) throws SQLiteAssetException {
        SQLiteDatabase db = returnDatabase();
        if (db != null) {
            // database already exists
            if (force) {
                Log.w(TAG, "forcing database upgrade!");
                copyDatabaseFromAssets();
                db = returnDatabase();
                db.setVersion(mNewVersion);
            }
            return db;
        } else {
            // database does not exist, copy it from assets and return it
            copyDatabaseFromAssets();
            db = returnDatabase();
            db.setVersion(mNewVersion);
            return db;
        }
    }

    private SQLiteDatabase returnDatabase() {
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(mDatabasePath + "/" + mName, mFactory, SQLiteDatabase.OPEN_READWRITE);
            Log.i(TAG, "successfully opened database " + mName);
            return db;
        } catch (SQLiteException e) {
            Log.w(TAG, "could not open database " + mName + " - " + e.getMessage());
            return null;
        }
    }

    private void copyDatabaseFromAssets() throws SQLiteAssetException {
        Log.e(TAG, "copying database from assets...");

        try {
            InputStream zipFileStream = mContext.getAssets().open(mArchivePath);
            File f = new File(mDatabasePath + "/");
            if (!f.exists()) {
                f.mkdir();
            }

            ZipInputStream zis = getFileFromZip(zipFileStream);
            if (zis == null) {
                throw new SQLiteAssetException("Archive is missing a SQLite database file");
            }
            writeExtractedFileToDisk(zis, new FileOutputStream(mDatabasePath + "/" + mName));

            Log.e(TAG, "database copy complete");

        } catch (FileNotFoundException fe) {
            SQLiteAssetException se = new SQLiteAssetException("Missing " + mArchivePath + " file in assets or target folder not writable");
            se.setStackTrace(fe.getStackTrace());
            throw se;
        } catch (IOException e) {
            SQLiteAssetException se = new SQLiteAssetException("Unable to extract " + mArchivePath + " to data directory");
            se.setStackTrace(e.getStackTrace());
            throw se;
        }
    }

    private void writeExtractedFileToDisk(ZipInputStream zin, OutputStream outs) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = zin.read(buffer)) > 0) {
            outs.write(buffer, 0, length);
        }
        outs.flush();
        outs.close();
        zin.close();
    }

    private ZipInputStream getFileFromZip(InputStream zipFileStream) throws FileNotFoundException, IOException {
        ZipInputStream zis = new ZipInputStream(zipFileStream);
        ZipEntry ze = null;
        while ((ze = zis.getNextEntry()) != null) {
            Log.e(TAG, "extracting file: '" + ze.getName() + "'...");
            return zis;
        }
        return null;
    }

    private class SQLiteAssetException extends Exception {
        public SQLiteAssetException(String s) {
            super(s);
        }
    }
}