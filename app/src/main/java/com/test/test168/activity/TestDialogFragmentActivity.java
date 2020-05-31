package com.test.test168.activity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.fragment.TestDialogFragment;
import com.test.test168.view.dialog.TestBottomSheetDialog;


public class TestDialogFragmentActivity extends BaseActivity {

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_test_dialog_fragment);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView(v);
            }
        };
        findViewById(R.id.btn_test_fragment_test1).setOnClickListener(onClickListener);
        findViewById(R.id.btn_test_fragment_test2).setOnClickListener(onClickListener);
        findViewById(R.id.btn_test_fragment_test3).setOnClickListener(onClickListener);
        findViewById(R.id.btn_test_fragment_test4).setOnClickListener(onClickListener);
    }

    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_test_fragment_test1:
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity, R.style.BaseDialog);
                builder.setCustomTitle(new TextView(mActivity));
                View inflate = LayoutInflater.from(mActivity).inflate(R.layout.fragment_test_dialog, null);
                builder.setView(inflate);
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_test_fragment_test2:
                TestDialogFragment dialogFragment = new TestDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "");
                break;
            case R.id.btn_test_fragment_test3:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mActivity);
                bottomSheetDialog.setContentView(R.layout.layout_test_bottom_sheet);
                bottomSheetDialog.show();
                break;
            case R.id.btn_test_fragment_test4:
                TestBottomSheetDialog testBottomSheetDialog = new TestBottomSheetDialog();
                testBottomSheetDialog.show(getSupportFragmentManager(), "TestBottomSheetDialog");
                break;
        }
    }
}
