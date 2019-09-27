package com.test.test168.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.test.test168.R;
import com.xian.common.utils.XLog;

public class IndexHeaderScrollBehavior extends CoordinatorLayout.Behavior<View> {

    private boolean isInited = false;
    private View headerLayout;
    private View alphaHideView;
    private View recyclerView;
    private View scaleWithMoveView;
    private View scaleHeightMoveImage;
    private View childFixedView1;
    private View childFixedView2;
    private View recyclerViewTop;
    private View recyclerViewBottom;

    private boolean isTop = true; // recyclerView 是否已经滑动到头部

    private float originalHeaderLayoutHeight = 0;
    private float originalHeaderLayoutBottom = 0;
    private float originalScaleMoveViewTop = 0;
    private float originalAlphaHideViewBottom = 0;
    private float originalScaleMoveImageBottom = 0;
    private float originalRecyclerViewTop = 0;// 原始的 recyclerView 的顶部位置
    private float canHeaderLayoutScrollYDistance = 0;// 顶部布局可滑动的距离
    private float mImageDistanceRatio = 1;// 背景图特殊的缩放比例
    private float fixedScaleWidth = 0;// 搜索框需要缩放的宽度大小

    public IndexHeaderScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {
        if (!isInited) {
            parent.post(() -> init(parent));
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    private void init(@NonNull CoordinatorLayout parent) {
        // 1. init view
        headerLayout = parent.findViewById(R.id.cl_custom_behavior);
        alphaHideView = parent.findViewById(R.id.locationText);
        recyclerView = parent.findViewById(R.id.recyclerView);
        scaleWithMoveView = parent.findViewById(R.id.searchLayout);
        scaleHeightMoveImage = parent.findViewById(R.id.iv_index_header_bg);
        childFixedView1 = parent.findViewById(R.id.iv_scan);
        childFixedView2 = parent.findViewById(R.id.iv_message);
        recyclerViewTop = scaleWithMoveView;
        recyclerViewBottom = parent.findViewById(R.id.fl_recycler_placeholder_bottom);
        // 2. location view
        recyclerView.setY(scaleWithMoveView.getBottom());// 定位 recyclerView 的位置
        // 3. record original view position
        originalScaleMoveViewTop = scaleWithMoveView.getTop();
        originalAlphaHideViewBottom = alphaHideView.getBottom();
        originalScaleMoveImageBottom = scaleHeightMoveImage.getBottom();
        originalRecyclerViewTop = recyclerView.getTop();
        originalHeaderLayoutHeight = headerLayout.getHeight();
        originalHeaderLayoutBottom = headerLayout.getBottom();
        canHeaderLayoutScrollYDistance = originalScaleMoveViewTop - alphaHideView.getTop();
        mImageDistanceRatio = (originalScaleMoveImageBottom - originalAlphaHideViewBottom) / canHeaderLayoutScrollYDistance;
        fixedScaleWidth = scaleWithMoveView.getRight() - childFixedView1.getLeft() + 10;
        XLog.i(" originalScaleMoveViewTop             : " + originalScaleMoveViewTop);
        XLog.i(" originalAlphaHideViewBottom          : " + originalAlphaHideViewBottom);
        XLog.i(" originalScaleMoveImageBottom         : " + originalScaleMoveImageBottom);
        XLog.i(" originalRecyclerViewTop              : " + originalRecyclerViewTop);
        XLog.i(" originalHeaderLayoutHeight           : " + originalHeaderLayoutHeight);
        XLog.i(" originalHeaderLayoutBottom           : " + originalHeaderLayoutBottom);
        XLog.i(" canHeaderLayoutScrollYDistance       : " + canHeaderLayoutScrollYDistance);
        XLog.i(" mImageDistanceRatio                  : " + mImageDistanceRatio);
        XLog.i(" fixedScaleWidth                      : " + fixedScaleWidth);
        isInited = true;
    }

    private void initViewsPosition(CoordinatorLayout parent) {
        float placeholderTop = recyclerViewTop.getBottom();
        XLog.i(" placeholderTop : " + placeholderTop);
        float placeholderBottom = recyclerViewBottom.getBottom();
        XLog.i(" placeholderBottom : " + placeholderBottom);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull View child, @NonNull View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed,
                               int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        //列表是否已经到最顶部
        isTop = dyUnconsumed < 0;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull View child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes,
                                       int type) {
        return target instanceof RecyclerView;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull View child, @NonNull View directTargetChild,
                                  int dx, int dy, @NonNull int[] consumed,
                                  int type) {

        if (!isInited) return;

        boolean scrollUp = dy > 0;// 标记是否向上滑动

        // 目前列表的位置
        float currentRecyclerViewTopPosition = recyclerView.getTop();
        // 已经移动的距离
        float currentRecyclerViewMoveYDistance = Math.abs(originalRecyclerViewTop - currentRecyclerViewTopPosition);
        // 已经移动距离所占可移动距离的比例
        float ratio = currentRecyclerViewMoveYDistance / canHeaderLayoutScrollYDistance;
        // 即将要移动到的目标位置
        float recyclerViewWillMoveToTopPosition = currentRecyclerViewTopPosition - dy;// 不明白这里为什么是 减。
        // 经过计算后，recyclerView 可移动的距离
        float recyclerViewResultMoveYDistance = 0;

        if (scrollUp) { // 向上
            if (recyclerViewWillMoveToTopPosition < originalRecyclerViewTop - canHeaderLayoutScrollYDistance) {
                recyclerViewResultMoveYDistance = canHeaderLayoutScrollYDistance - currentRecyclerViewMoveYDistance;
            } else {
                recyclerViewResultMoveYDistance = dy;
            }
            if (currentRecyclerViewMoveYDistance < canHeaderLayoutScrollYDistance) {
                float resultDy = -Math.abs(recyclerViewResultMoveYDistance);
                consumed[1] = (int) resultDy;
                updateView(ratio, resultDy);
            }
        } else { // 向下
            if (isTop) {
                if (recyclerViewWillMoveToTopPosition > originalRecyclerViewTop) {
                    recyclerViewResultMoveYDistance = originalRecyclerViewTop - currentRecyclerViewTopPosition;
                } else {
                    recyclerViewResultMoveYDistance = dy;
                }
                if (currentRecyclerViewTopPosition < originalRecyclerViewTop) {
                    float resultDy = Math.abs(recyclerViewResultMoveYDistance);
                    consumed[1] = (int) resultDy;
                    updateView(ratio, resultDy);
                }
            }
        }

    }

    private void updateView(float ratio, float moveViewYResult) {

        float alpha = 1 - ratio;
        alpha = alpha < 0.1 ? 0 : alpha > 0.9 ? 1 : alpha;
        XLog.i(" updateView ratio : " + ratio);
        XLog.i(" updateView alpha : " + alpha);
        XLog.i(" updateView moveViewYResult : " + moveViewYResult);
        scaleWithMoveView.setPivotX(0);
        scaleWithMoveView.setPivotY(0);
        if (ratio > 0) {
//            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) scaleWithMoveView.getLayoutParams();
//            // layoutParams.rightMargin = (int) (fixedScaleWidth * ratio);
//            if (moveViewYResult < 0) {
//                layoutParams.width = (int) (layoutParams.width - fixedScaleWidth * ratio);
//            } else {
//                layoutParams.width = (int) (layoutParams.width + fixedScaleWidth * ratio);
//            }
//            scaleWithMoveView.setLayoutParams(layoutParams);
//            if (moveViewYResult < 0) {
//                ViewCompat.animate(scaleWithMoveView).scaleX(-ratio);
//            } else {
//                ViewCompat.animate(scaleWithMoveView).scaleX(ratio);
//            }
        }

        //        ViewCompat.offsetTopAndBottom(headerLayout, (int) moveViewYResult);
        ViewCompat.offsetTopAndBottom(scaleWithMoveView, (int) moveViewYResult);
        ViewCompat.offsetTopAndBottom(recyclerView, (int) moveViewYResult);

        ViewCompat.offsetTopAndBottom(scaleHeightMoveImage, (int) (moveViewYResult * mImageDistanceRatio));
        scaleHeightMoveImage.setAlpha(alpha);

        // scaleWithMoveView.setScaleX(ratio);
        float ratioWidth = fixedScaleWidth / scaleWithMoveView.getWidth();
//        scaleWithMoveView

//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) recyclerView.getLayoutParams();
//        recyclerView.setLayoutParams(lp);

        alphaHideView.setAlpha(alpha);
        alphaHideView.setVisibility(ratio <= 0 ? View.INVISIBLE : View.VISIBLE);
        // 下面几个 view 保持不动，所以做反向移动
//        ViewCompat.offsetTopAndBottom(alphaHideView, -(int) moveViewYResult);
//        ViewCompat.offsetTopAndBottom(childFixedView1, -(int) moveViewYResult);
//        ViewCompat.offsetTopAndBottom(childFixedView2, -(int) moveViewYResult);

    }


}