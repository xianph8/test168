package com.test.test168.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.utils.ColorHelper;
import com.xian.common.utils.XLog;


/**
 * 仅用在首页头部的滑动效果
 */
public class IndexHeaderScrollBehavior extends CoordinatorLayout.Behavior<View> {

    private View headerTitleView;
    private View headerLayoutBgImage;
    private ImageView childFixedView1;
    private ImageView childFixedView2;
    private View recyclerView;
    private ViewGroup searchLayoutView;
    private ImageView searchLayoutIconView;

    private boolean isInited = false;// 记录是否已经初始化过
    private boolean isTop = true; // recyclerView 是否已经滑动到头部

    private float originalHeaderTitleViewBottom = 0;
    private float originalHeaderTitleViewTop = 0;
    private float originalHeaderLayoutBgImageBottom = 0;
    private float originalHeaderLayoutBgImageTop = 0;
    private float originalSearchLayoutViewTop = 0;
    private float originalSearchLayoutViewWidth = 0;
    private float originalRecyclerViewTop = 0;// 原始的 recyclerView 的顶部位置
    private float canHeaderLayoutScrollYDistance = 0;// 顶部布局可滑动的距离
    private float fixedScaleWidth = 0;// 搜索框需要缩放的宽度大小

    public IndexHeaderScrollBehavior() {
    }

    public IndexHeaderScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
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
        headerTitleView = parent.findViewById(R.id.locationText);
        recyclerView = parent.findViewById(R.id.recyclerView);
        searchLayoutView = parent.findViewById(R.id.searchLayout);
        searchLayoutIconView = parent.findViewById(R.id.iv_search_layout_icon);
        headerLayoutBgImage = parent.findViewById(R.id.iv_header_bg);
        childFixedView1 = parent.findViewById(R.id.iv_scan);
        childFixedView2 = parent.findViewById(R.id.iv_message);
        // 2. location view
        recyclerView.setY(getBottom(searchLayoutView) + dp2px(parent.getContext(), 15));// 定位 recyclerView 的位置
        // 3. record original view position
        originalSearchLayoutViewTop = getTop(searchLayoutView);
        originalSearchLayoutViewWidth = searchLayoutView.getWidth();
        originalHeaderTitleViewTop = getTop(headerTitleView) + dp2px(parent.getContext(), 3);
        originalHeaderTitleViewBottom = getBottom(headerTitleView);
        originalHeaderLayoutBgImageTop = getTop(headerLayoutBgImage);
        originalHeaderLayoutBgImageBottom = getBottom(headerLayoutBgImage);
        originalRecyclerViewTop = getTop(recyclerView);
        canHeaderLayoutScrollYDistance = originalSearchLayoutViewTop - originalHeaderTitleViewTop;
        fixedScaleWidth = getRight(searchLayoutView) - childFixedView1.getX() + dp2px(childFixedView1.getContext(), 3);
        isInited = true;
    }

    public float getRight(View view) {
        return view.getX() + view.getMeasuredWidth();
    }

    public float getTop(View view) {
        return view.getY();
    }

    public float getBottom(View view) {
        return view.getY() + view.getMeasuredHeight();
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
        // 即将要移动到的目标位置
        float searchLayoutWillMoveToTopPosition = currentSearchLayoutTopPosition - dy;
        // 即将要移动到目标位置的比例（1 是最终位置，0 是起始位置）
        float willMoveYDistanceRatio = Math.abs(originalSearchLayoutViewTop - searchLayoutWillMoveToTopPosition) / canHeaderLayoutScrollYDistance;
        willMoveYDistanceRatio = willMoveYDistanceRatio > 1 ? 1 : willMoveYDistanceRatio < 0 ? 0 : willMoveYDistanceRatio;

        if (scrollUp) { // 向上
            if (searchLayoutWillMoveToTopPosition < originalHeaderTitleViewTop) { // 如果移动后会超过最终的位置，就直接移动到最终位置
                updateView(1);
            } else {
                updateView(willMoveYDistanceRatio);
                consumed[1] = dy;
            }
        } else if (isTop) {// 向下 and 并且判断 recycler view 是否已经显示第一个 item
            if (searchLayoutWillMoveToTopPosition > originalSearchLayoutViewTop) {// 如果移动后会超过原来的位置，就直接移动到原始位置
                updateView(0);
            } else {
                updateView(willMoveYDistanceRatio);
                consumed[1] = dy;
            }
        }
    }

    private void updateView(float targetRatio) {

        float targetAlpha = 1 - targetRatio;
        targetAlpha = targetAlpha < 0.1 ? 0 : targetAlpha > 0.9 ? 1 : targetAlpha;

        float bgImageDistance = originalHeaderLayoutBgImageBottom - originalHeaderTitleViewBottom;
        headerLayoutBgImage.setY(originalHeaderLayoutBgImageTop - (bgImageDistance * targetRatio));
        headerLayoutBgImage.setAlpha(targetAlpha);

        headerTitleView.setAlpha(targetAlpha);

        int fixedViewColor = ColorHelper.evaluateColor(0xff555555, 0xffffffff, targetAlpha);
        ColorStateList fixedViewColorStateList = ColorHelper.createColorStateList(fixedViewColor);
        int searchLayoutBgColor = ColorHelper.evaluateColor(0xCCF2F2F2, 0xFFFFE9EB, targetAlpha);
        ColorStateList searchLayoutBgColorStateList = ColorHelper.createColorStateList(searchLayoutBgColor);
        int searchLayoutIconColor = ColorHelper.evaluateColor(0xff555555, 0xFFEA6E7A, targetAlpha);
        ColorStateList searchLayoutIconColorStateList = ColorHelper.createColorStateList(searchLayoutIconColor);
        int searchLayoutTextColor = ColorHelper.evaluateColor(0xFFC0C0C0, 0xFFEA6471, targetAlpha);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            childFixedView1.setImageTintList(fixedViewColorStateList);
            childFixedView2.setImageTintList(fixedViewColorStateList);
            searchLayoutIconView.setImageTintList(searchLayoutIconColorStateList);
            searchLayoutView.setBackgroundTintList(searchLayoutBgColorStateList);
            for (int i = 0; i < searchLayoutView.getChildCount(); i++) {
                View searchChild = searchLayoutView.getChildAt(i);
                updateChildTextViewTextColor(searchLayoutTextColor, searchChild);
            }
        }

        if (targetRatio >= 0) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) searchLayoutView.getLayoutParams();
            layoutParams.width = (int) (originalSearchLayoutViewWidth - (fixedScaleWidth * targetRatio));
            searchLayoutView.setLayoutParams(layoutParams);
        }

        searchLayoutView.setY(originalSearchLayoutViewTop - canHeaderLayoutScrollYDistance * targetRatio);
        recyclerView.setY(originalRecyclerViewTop - canHeaderLayoutScrollYDistance * targetRatio);

    }

    private void updateChildTextViewTextColor(int searchLayoutTextColor, View searchChild) {
        if (searchChild instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) searchChild).getChildCount(); i++) {
                updateChildTextViewTextColor(searchLayoutTextColor, ((ViewGroup) searchChild).getChildAt(i));
            }
        }
        if (searchChild instanceof TextView) {
            ((TextView) searchChild).setTextColor(searchLayoutTextColor);
            ((TextView) searchChild).setHintTextColor(searchLayoutTextColor);
        }
    }

}