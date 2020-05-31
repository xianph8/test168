package com.test.test168.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.View;

import com.test.test168.R;

/**
 * Created by w07 on 2016/12/28 17:53
 * Description :
 */

public class TestBottomSheetDialog extends BottomSheetDialogFragment {

    public TestBottomSheetDialog() {

//        BottomSheetDialog dialog = (BottomSheetDialog) this.getDialog();
//
//        dialog.setContentView(R.layout.layout_test_bottom_sheet);

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.layout_test_bottom_sheet, null);
        dialog.setContentView(contentView);

//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
//        CoordinatorLayout.Behavior behavior = params.getBehavior();
//
//        if (behavior != null && behavior instanceof BottomSheetBehavior) {
//            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mCallback);
//        }
    }

    private BottomSheetBehavior.BottomSheetCallback mCallback =
            new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {
                        case BottomSheetBehavior.STATE_DRAGGING:
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            break;
                        case BottomSheetBehavior.STATE_HIDDEN:
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            };
}
