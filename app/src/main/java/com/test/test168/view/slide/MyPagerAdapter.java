package com.test.test168.view.slide;

import android.os.Parcelable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Test on 2016/7/5.     * 轮播控件的主要逻辑
 */

class MyPagerAdapter extends PagerAdapter {


    private List<ImageView> imageViewsList;

    public MyPagerAdapter(List<ImageView> imageViewsList) {
        this.imageViewsList = imageViewsList;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        if (imageViewsList.size() <= 0) return;
        ((ViewPager) container).removeView(imageViewsList.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        if (imageViewsList.size() <= 0) return null;
        ((ViewPager) container).addView(imageViewsList.get(position));
        return imageViewsList.get(position);
    }

    @Override
    public int getCount() {
        if (imageViewsList.size() <= 0) return 0;
        return imageViewsList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
    }

    @Override
    public void finishUpdate(View arg0) {
    }

}
