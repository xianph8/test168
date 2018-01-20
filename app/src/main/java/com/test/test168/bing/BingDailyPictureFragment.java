package com.test.test168.bing;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import com.test.test168.R;
import com.test.test168.base.BaseFragment;
import com.test.test168.bean.BingDailyPicture;
import com.xian.common.utils.XLog;


/**
 * Created by King on 2017/3/18.
 */

public class BingDailyPictureFragment extends BaseFragment {


    private static final String ARG_SECTION_NUMBER = "section_number";
    AppCompatImageView ivDailyPic;

    public static BingDailyPictureFragment newInstance(int sectionNumber) {
        BingDailyPictureFragment fragment = new BingDailyPictureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_bing_daily_pic;
    }

    @Override
    protected void initViews() {
        ivDailyPic = root.findViewById(R.id.iv_daily_pic);
        int section = getArguments().getInt(ARG_SECTION_NUMBER);
        showToast("section : " + section);
        XLog.i(" url : " + "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN");

        BingDailyPictureLoader loader = new BingDailyPictureLoader(mContext);
        loader.load(section, new BingSub<BingDailyPicture>() {
            @Override
            protected void onSuccess(BingDailyPicture result) {
                dismissLoadingDialog();
                XLog.i(" on success : " + result);
            }

            @Override
            protected void onFailure(String errorMsg) {
                dismissLoadingDialog();
                XLog.i(" on failure : " + errorMsg);
            }
        });


    }

}
