package com.test.test168.arch;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.test.test168.R;
import com.test.test168.arch.model.DetailsUI;
import com.test.test168.base.BaseLoadingFragment;
import com.test.test168.bean.Response;

import java.util.List;

import io.reactivex.functions.Consumer;

public class TestBaseViewModelFragment extends BaseLoadingFragment {

    private TestBaseViewModelViewModel mViewModel;

    public static TestBaseViewModelFragment newInstance() {
        return new TestBaseViewModelFragment();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.test_base_view_model_fragment;
    }

    @Override
    protected void initViews() {
        mViewModel = ViewModelProviders.of(this).get(TestBaseViewModelViewModel.class);

        // 列表类型的数据建议如下方式：
        mViewModel.refresh();
        mViewModel.subListData().observe(this, new BaseLoadingObserver<List<String>>() {
            // 需要哪个事件就覆写哪个事件，看 里面的方法
            @Override
            protected void onSuccess(@NonNull List<String> data) {
                // show list to recycler view
            }
        });


        // 详情类型数据建议如下处理：
        // 建议在需要长时间交互的地方使用这种 liveData 的方式
        mViewModel.loadDetails();
        mViewModel.subDetailsData().observe(this, new BaseLoadingObserver<DetailsUI>() {
            // 需要哪个事件就覆写哪个事件，看 里面的方法
            @Override
            protected void onSuccess(@NonNull DetailsUI data) {
                // set data to view
            }
        });

        // 模拟提交操作（一些仅一次性的操作可以用这种方式）
        findViewById(R.id.btn_test2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submit();
                    }
                });

    }

    @SuppressLint("CheckResult")
    private void submit() {
        mViewModel.submitInfo("name", "signature", "age")
                .subscribe(new Consumer<Response<String>>() {
                    @Override
                    public void accept(Response<String> stringResponse) throws Exception {
                        if (stringResponse.isSuccess()) {
                            showToast("成功");
                            // finish activity or other
                        } else {
                            showToast("操作失败：" + stringResponse.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToast("操作失败：" + throwable.getMessage());
                    }
                });
    }

}
