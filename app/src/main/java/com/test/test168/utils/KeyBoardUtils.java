package com.test.test168.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

// open or close Inputmethod
public class KeyBoardUtils {

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeyboard(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm = null;
        } else {
            imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeyboard(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        } else {
            imm = null;
        }
    }
}
