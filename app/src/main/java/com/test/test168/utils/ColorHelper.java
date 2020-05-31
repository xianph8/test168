package com.test.test168.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.annotation.ColorInt;

public final class ColorHelper {

    // TODO: 2018/10/22 rename to DEFAULT_COLOR
    public static final int INVALID_COLOR = Color.TRANSPARENT;

    private ColorHelper() {
    }

    @ColorInt
    public static int parseColor(String colorString) {
        try {
            return Color.parseColor(colorString);
        } catch (Exception e) {
            return INVALID_COLOR;
        }
    }

    public static int evaluateColor(int startValue, int endValue, float fraction) {
        if (fraction <= 0) {
            return startValue;
        }
        if (fraction >= 1) {
            return endValue;
        }
        int startInt = startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return ((startA + (int) (fraction * (endA - startA))) << 24) | ((startR + (int) (fraction * (endR - startR))) << 16) | ((startG + (int) (fraction * (endG - startG))) << 8) | ((startB + (int) (fraction * (endB - startB))));
    }

    /**
     * 创建按钮文字点击样式
     *
     * @param normal  正常样式
     * @param pressed 按下样式
     * @param focused 焦点样式
     * @param unable  不可用样式
     * @return ColorStateList
     */
    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[]{pressed, focused, normal, focused, unable, normal};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }

    public static ColorStateList createColorStateList(int normal) {
        return createColorStateList(normal, normal, normal, normal);
    }
}
