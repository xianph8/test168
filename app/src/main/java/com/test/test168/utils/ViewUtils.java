package com.test.test168.utils;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by King on 2016/3/25.
 */
public class ViewUtils {


    public static String getViewText(View view) {
        String text = "";
        if (view instanceof TextView) {
            text = ((TextView) view).getText().toString().trim();
        } else if (view instanceof EditText) {
            text = ((EditText) view).getText().toString().trim();
        }
        return text;
    }

    public static boolean isNull(String str) {
        boolean isnull = false;
        if (null == str || str.equals(""))
            isnull = true;
        return isnull;
    }

    public static boolean isNull(View view) {
        String str = getViewText(view);
        boolean isnull = false;
        if (null == str || str.equals(""))
            isnull = true;
        return isnull;
    }

}
