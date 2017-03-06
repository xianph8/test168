package com.test.test168.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.test168.R;
import com.test.test168.adapter.JuheHealthNewsClassAdapter;
import com.test.test168.api.JuheApi;
import com.test.test168.base.BaseActivity;
import com.test.test168.bean.JuheHealthNewsClass;
import com.test.test168.network.CustomJuheSub;
import com.test.test168.network.JuheApiWrapper;
import com.test.test168.utils.L;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 聚合数据接口
 */
public class JuheActivity extends BaseActivity {

    @Bind(R.id.rv_news_class)
    RecyclerView rvNewsClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juhe);
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btn_start)
    public void onRequest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", JuheApi.key);
        map.put("dtype", JuheApi.dtype);
        JuheApiWrapper.getInstance()
                .create(JuheApi.class)
                .getNewsClass(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomJuheSub<List<JuheHealthNewsClass>>() {
                    @Override
                    protected void onSuccess(List<JuheHealthNewsClass> result) {
                        L.i("on success : " + result);
                        setListView(result);
                    }

                    @Override
                    protected void onFailure(String errorMsg) {
                        L.e(errorMsg);
                    }
                });
    }

    private void setListView(List<JuheHealthNewsClass> result) {
        rvNewsClass.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNewsClass.setAdapter(new JuheHealthNewsClassAdapter(mActivity, result) {
            @Override
            public void onItemClick(JuheHealthNewsClass list, int position) {
                L.i(" item : " + list);
            }
        });
    }


}
