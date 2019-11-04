package com.test.test168.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.test.test168.R;
import com.xian.common.utils.XLog;

public class IndexSmartRefreshHeaderAnimatorView extends FrameLayout implements RefreshHeader {

    private IndexSmartRefreshHeaderView mAnimationView;
    private TextView mAnimationRefreshTipsView;

    public IndexSmartRefreshHeaderAnimatorView(Context context) {
        super(context);
        initView(context);
    }

    public IndexSmartRefreshHeaderAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public IndexSmartRefreshHeaderAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    private void initView(Context context) {
        View customView = LayoutInflater.from(context).inflate(R.layout.custom_view_animator_header, this);
        ImageView img = customView.findViewById(R.id.iv_custom_view_animator_header_bg);
        img.setImageResource(R.drawable.def_bg);
        mAnimationView = customView.findViewById(R.id.chrv_custom_view_animator_header_refresh_view);
        mAnimationRefreshTipsView = customView.findViewById(R.id.tv_custom_view_animator_header_tips_view);
    }

    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void setPrimaryColors(int... colors) {
        // 不管颜色
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
       i(" onInitialized : " + kernel);
       i(" onInitialized : " + height);
       i(" onInitialized : " + maxDragHeight);
    }

    private void i(String log){
        Log.i("IndexSmartRefreshView", "i: "+log);
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
       i(" onMoving : " + isDragging);
       i(" onMoving : " + percent);
       i(" onMoving : " + offset);
       i(" onMoving : " + height);
       i(" onMoving : " + maxDragHeight);

        if (isDragging) {
            mAnimationView.updatePercent(percent > 1 ? 1 : percent);
            mAnimationRefreshTipsView.setVisibility(percent > 0.8f ? View.VISIBLE : View.GONE);
        } else {
            mAnimationRefreshTipsView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
       i(" onReleased : " + refreshLayout);
       i(" onReleased : " + height);
       i(" onReleased : " + maxDragHeight);

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
       i(" onStartAnimator : " + refreshLayout);
       i(" onStartAnimator : " + height);
       i(" onStartAnimator : " + maxDragHeight);

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
       i(" onFinish : " + refreshLayout);
       i(" onFinish : " + success);
        if (success) {
        } else {
        }
        mAnimationView.stopRefreshingAnimation();
        mAnimationView.showFinishAnimation();
        return 500;// 延迟500毫秒之后再弹回
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
        // 下面 isSupportHorizontalDrag 返回 false ，这里不用管
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;// 不支持水平拖动
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
       i(" onStateChanged : " + refreshLayout);
       i(" onStateChanged : " + oldState);
       i(" onStateChanged : " + newState.toString());
        if (newState.toHeader().isOpening) {
            mAnimationView.startRefreshingAnimation();
        }
    }

}