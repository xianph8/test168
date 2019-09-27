package com.test.test168.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.test.test168.R;

/**
 * 自定义 CoordinatorLayout 的 Behavior， 泛型为观察者 View ( 要跟着别人动的那个 )
 * <p>
 * 必须重写两个方法，layoutDependOn和onDependentViewChanged
 *
 * @author nanchen
 * @fileName CoordinatorLayoutDemo
 * @date 2016/12/13  10:13
 */
public class DependentRecyclerViewPositionBehavior extends CoordinatorLayout.Behavior<View> {

    /**
     * 构造方法
     */
    public DependentRecyclerViewPositionBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(@NonNull CoordinatorLayout parent, @NonNull View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {
        initViewsPosition(parent);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    private void initViewsPosition(CoordinatorLayout parent) {
        View recyclerView = parent.findViewById(R.id.recyclerView);
        View placeholder = parent.findViewById(R.id.fl_recycler_placeholder_top);
        float placeholderTop = placeholder.getY();
        recyclerView.setY(placeholderTop);
        View recyclerViewBottom = parent.findViewById(R.id.fl_recycler_placeholder_bottom);
        float placeholderBottom = recyclerViewBottom.getY();
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) recyclerView.getLayoutParams();
        lp.height = (int) (placeholderBottom - placeholderTop);
        recyclerView.setLayoutParams(lp);
        // placeholder.setPadding(placeholder.getPaddingLeft(), placeholder.getPaddingTop(), placeholder.getPaddingRight(), placeholder.getPaddingBottom() + 50);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
//
//        float alphaHideViewHeight = alphaHideView.getHeight();
//        float alphaResult = 1;
//
//        float moveViewYResult = 0;
//
//        float scaleWithResult = 0;
//
//        float absDY = Math.abs(dy);
//
//        float alphaHideViewTop = alphaHideView.getTop() == 0 ? 1 : alphaHideView.getTop();// 有可能这个固定的 View 的位置会在最顶部，也就是 top 的值会为 0
//        float scaleWithMoveViewTop = scaleWithMoveView.getTop(); // 这个是移动的 view 的 top 点，每次都会变化
//
//        if (dy > 0) { // 向上
//            moveViewYResult = -Math.abs((scaleWithMoveViewTop - absDY) < alphaHideViewTop ? 0 : minInterval);
//
//        } else if (dy < 0) { // 向下
//            moveViewYResult = Math.abs((scaleWithMoveViewTop + absDY) > alphaHideView.getBottom() ? 0 : minInterval);
//
//        }
//
//        alphaResult = Math.abs(originalScaleMoveViewY - scaleWithMoveViewTop) / Math.abs(originalScaleMoveViewY - alphaHideViewTop);
//
//        XLog.i(" alphaResult : " + alphaResult);
//        XLog.i(" moveViewYResult : " + moveViewYResult);
//        XLog.i(" scaleWithResult : " + scaleWithResult);
//
//        alphaHideView.setAlpha(alphaResult);
//        alphaHideView.setVisibility(alphaResult <= 0 ? View.INVISIBLE : View.VISIBLE);
//
//        if (moveViewYResult != 0) {
//            if (moveViewYResult > 0) {
//                if (isTop && recyclerView.getY() < 500) {
//                    ViewCompat.offsetTopAndBottom(coordinatorLayout, (int) moveViewYResult);
//                } else {
//                    consumed[1] = dy;// 如果有移动就把距离都消耗完，不让 recyclerview 滑动
//                }
//            } else if (moveViewYResult < 0) {
//                consumed[1] = dy;// 如果有移动就把距离都消耗完，不让 recyclerview 滑动
//                ViewCompat.offsetTopAndBottom(coordinatorLayout, (int) moveViewYResult);
//                ViewCompat.offsetTopAndBottom(alphaHideView, -(int) moveViewYResult);
//                ViewCompat.offsetTopAndBottom(childFixedView1, -(int) moveViewYResult);
//                ViewCompat.offsetTopAndBottom(childFixedView2, -(int) moveViewYResult);
//            }
//        }

    }
}