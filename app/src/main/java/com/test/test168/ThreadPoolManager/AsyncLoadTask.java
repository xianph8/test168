package com.test.test168.ThreadPoolManager;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;

/**
 * Created by w07 on 2017/1/24 14:57
 * Description :
 */


public class AsyncLoadTask extends AsyncTask<Integer, Void, Pair<Integer, Bitmap>> {

    private static final String TAG = "AsyncLoadTask";

    /** 要刷新的view */
    private ImageView view;

    public AsyncLoadTask(ImageView view) {
        this.view = view;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Pair<Integer, Bitmap> doInBackground(Integer... params) {
        int position = params[0];
        String imageUrl = ImageHelper.getImageUrl("", position);
        Log.i(TAG, "AsyncLoad from NET :" + imageUrl);
        Bitmap bitmap = ImageHelper.loadBitmapFromNet(imageUrl);
        return new Pair<Integer, Bitmap>(position, bitmap);
    }

    @Override
    protected void onPostExecute(Pair<Integer, Bitmap> result) {
//        if (result.first == view.position) {//这句才是对的，下面是错的
        if (result.first == view.getBaseline()) {
            view.setImageBitmap(result.second);
        }
    }

}