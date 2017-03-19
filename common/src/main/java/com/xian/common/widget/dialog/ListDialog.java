package com.xian.common.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.xian.common.R;


/**
 * Created by w07 on 2016/10/22.
 */

public class ListDialog {

    public static AlertDialog show(Context mContext, String title, String[] multiChoiceItems, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomAlertDialog);
        builder.setTitle(title);
        builder.setSingleChoiceItems(multiChoiceItems, 0, listener);
        return builder.create();
    }
}
