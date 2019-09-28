package com.test.test168.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.test.test168.R;
import com.xian.common.utils.XLog;

public class IndexHeaderScrollBehavior extends CoordinatorLayout.Behavior<View> {

    private boolean isInited = false;
    private View headerLayout;
    private View headerTitleView;
    private View headerLayoutBgImage;
    private View childFixedView1;
    private View childFixedView2;
    private View recyclerView;
    private View searchLayoutView;

    private boolean isTop = true; // recyclerView 是否已经滑动到头部

    private float originalHeaderLayoutHeight = 0;
    private float originalHeaderLayoutBottom = 0;
    private float originalHeaderTitleViewBottom = 0;
    private float originalHeaderTitleViewTop = 0;
    private float originalHeaderLayoutBgImageBottom = 0;
    private float originalSearchLayoutViewTop = 0;
    private float originalSearchLayoutViewWidth = 0;
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
        headerLayout = parent.findViewById(R.id.cl_header_content_layout);
        headerTitleView = parent.findViewById(R.id.locationText);
        recyclerView = parent.findViewById(R.id.recyclerView);
        searchLayoutView = parent.findViewById(R.id.searchLayout);
        headerLayoutBgImage = parent.findViewById(R.id.iv_index_header_bg);
        childFixedView1 = parent.findViewById(R.id.iv_scan);
        childFixedView2 = parent.findViewById(R.id.iv_message);
        // 2. location view
        recyclerView.setY(getBottom(searchLayoutView) + 10);// 定位 recyclerView 的位置
        // 3. record original view position
        originalSearchLayoutViewTop = getTop(searchLayoutView);
        originalSearchLayoutViewWidth = searchLayoutView.getWidth();
        originalHeaderTitleViewTop = getTop(headerTitleView);
        originalHeaderTitleViewBottom = getBottom(headerTitleView);
        originalHeaderLayoutBgImageBottom = getBottom(headerLayoutBgImage);
        originalRecyclerViewTop = getTop(recyclerView);
        originalHeaderLayoutHeight = headerLayout.getHeight();
        originalHeaderLayoutBottom = getBottom(headerLayout);
        canHeaderLayoutScrollYDistance = originalSearchLayoutViewTop - getTop(headerTitleView);
        mImageDistanceRatio = (originalHeaderLayoutBgImageBottom - originalHeaderTitleViewBottom) / canHeaderLayoutScrollYDistance;
        fixedScaleWidth = getRight(searchLayoutView) - childFixedView1.getX() + 20;
        XLog.i(" originalSearchLayoutViewTop          : " + originalSearchLayoutViewTop);
        XLog.i(" originalSearchLayoutViewWidth        : " + originalSearchLayoutViewWidth);
        XLog.i(" originalHeaderTitleViewBottom        : " + originalHeaderTitleViewBottom);
        XLog.i(" originalHeaderLayoutBgImageBottom    : " + originalHeaderLayoutBgImageBottom);
        XLog.i(" originalRecyclerViewTop              : " + originalRecyclerViewTop);
        XLog.i(" originalHeaderLayoutHeight           : " + originalHeaderLayoutHeight);
        XLog.i(" originalHeaderLayoutBottom           : " + originalHeaderLayoutBottom);
        XLog.i(" canHeaderLayoutScrollYDistance       : " + canHeaderLayoutScrollYDistance);
        XLog.i(" mImageDistanceRatio                  : " + mImageDistanceRatio);
        XLog.i(" fixedScaleWidth                      : " + fixedScaleWidth);
        isInited = true;
    }

    public float getRight(View view) {
        return view.getX() + view.getWidth();
    }

    public float getTop(View view) {
        return view.getY();
    }

    public float getBottom(View view) {
        return view.getY() + view.getHeight();
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
                                       @NonNull View target, int nestedScrollAxes,
                                       int type) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull View child, @NonNull View directTargetChild,
                                  int dx, int dy, @NonNull int[] consumed,
                                  int type) {

        if (!isInited) return;

        boolean scrollUp = dy > 0;// 标记是否向上滑动
        XLog.i(" onNestedPreScroll dy " + dy);

        // 目前列表的位置
        float currentSearchLayoutTopPosition = getTop(searchLayoutView);
        // 已经移动的距离
        float currentSearchLayoutMoveYDistance = Math.abs(originalSearchLayoutViewTop - currentSearchLayoutTopPosition);
        // 即将要移动到的目标位置
        float searchLayoutWillMoveToTopPosition = currentSearchLayoutTopPosition - dy;
        // 经过计算后，recyclerView 可移动的距离
        float searchLayoutMoveYDistanceResult = 0; // 正值为向下移动，负值为向上移动

        float currentMoveYDistanceRatio = currentSearchLayoutMoveYDistance / canHeaderLayoutScrollYDistance;
        currentMoveYDistanceRatio = currentMoveYDistanceRatio > 1 ? 1 : currentMoveYDistanceRatio < 0 ? 0 : currentMoveYDistanceRatio;

        if (scrollUp) { // 向上
            if (searchLayoutWillMoveToTopPosition < originalHeaderTitleViewTop) { // 如果移动后会超过原来的位置，就重新计算移动的幅度幅度
               //float finalDistance = -Math.abs(currentSearchLayoutTopPosition - originalHeaderTitleViewTop);
               //updateView(0, finalDistance);
               //consumed[1] = Math.abs((int) (Math.abs(dy) - Math.abs(finalDistance)));
            } else {
                searchLayoutMoveYDistanceResult = -Math.abs(dy);
                updateView(currentMoveYDistanceRatio, searchLayoutMoveYDistanceResult);
                consumed[1] = dy;
            }
        } else if (isTop) {// 向下 and recycler view visibility fist item
            if (searchLayoutWillMoveToTopPosition > originalSearchLayoutViewTop) {// 如果移动后会超过原来的位置，就重新计算移动的幅度幅度
               // float finalDistance = Math.abs(originalHeaderTitleViewTop - currentSearchLayoutTopPosition);
               // updateView(1, finalDistance);
               // consumed[1] = -Math.abs((int) (Math.abs(dy) - finalDistance));
            } else {
                searchLayoutMoveYDistanceResult = Math.abs(dy);
                updateView(currentMoveYDistanceRatio, searchLayoutMoveYDistanceResult);
                consumed[1] = dy;
            }
        }
    }

    private void updateView(float ratio, float moveViewYDistanceResult) {

        float alpha = 1 - ratio;
        alpha = alpha < 0.2 ? 0 : alpha > 0.8 ? 1 : alpha;
        XLog.i(" updateView ratio : " + ratio);
        XLog.i(" updateView moveViewYResult : " + moveViewYDistanceResult);

        headerLayoutBgImage.setY(getTop(headerLayoutBgImage) + (moveViewYDistanceResult * mImageDistanceRatio));
        headerLayoutBgImage.setAlpha(alpha);

        headerTitleView.setAlpha(alpha);
        headerTitleView.setVisibility(ratio <= 0 ? View.INVISIBLE : View.VISIBLE);

        if (ratio > 0) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) searchLayoutView.getLayoutParams();
            if (moveViewYDistanceResult < 0) {// up
                layoutParams.width = (int) (originalSearchLayoutViewWidth - fixedScaleWidth * ratio);
            } else {// down
                float targetSearchLayoutWidth = layoutParams.width + (fixedScaleWidth - fixedScaleWidth * ratio);
                layoutParams.width = (int) (targetSearchLayoutWidth > originalSearchLayoutViewWidth ? originalSearchLayoutViewWidth : targetSearchLayoutWidth);
            }
            searchLayoutView.setLayoutParams(layoutParams);
        }

        searchLayoutView.setY(getTop(searchLayoutView) + moveViewYDistanceResult);
        recyclerView.setY(getTop(recyclerView) + moveViewYDistanceResult);

    }


}