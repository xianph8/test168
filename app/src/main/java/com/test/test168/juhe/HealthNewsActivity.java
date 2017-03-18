package com.test.test168.juhe;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.TConstants;
import com.test.test168.adapter.JuheHealthNewsClassAdapter;
import com.test.test168.api.JuheApi;
import com.test.test168.base.BaseActivity;
import com.test.test168.bean.JuheHealthNewsClass;
import com.test.test168.bean.JuheResultBean;
import com.test.test168.network.CustomJuheSub;
import com.test.test168.network.JuheApiWrapper;
import com.test.test168.utils.XLog;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 聚合数据接口
 */
public class HealthNewsActivity extends BaseActivity {

    @Bind(R.id.rv_news_class)
    RecyclerView rvNewsClass;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_health_news);
        ButterKnife.bind(this);

        ((TextView) $(R.id.include_head_title)).setText("健康资讯");

        onRequest();
    }

    public void onRequest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", JuheApi.key);
        map.put("dtype", JuheApi.dtype);
        JuheApiWrapper.getInstance()
                .create(JuheApi.class)
                .getNewsClass(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomJuheSub<JuheResultBean<JuheHealthNewsClass>>() {
                    @Override
                    protected void onSuccess(JuheResultBean<JuheHealthNewsClass> result) {
                        XLog.i("on success : " + result);
                        setListView(result.list.tList);
                    }

                    @Override
                    protected void onFailure(String errorMsg) {
                        XLog.e(errorMsg);
                    }
                });
    }

    private void setListView(List<JuheHealthNewsClass> result) {
        rvNewsClass.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNewsClass.setAdapter(new JuheHealthNewsClassAdapter(mActivity, result) {
            @Override
            public void onItemClick(JuheHealthNewsClass item, int position) {
//                XLog.i(" item : " + list);
                Intent intent = new Intent(mActivity, HealthNewsClassListActivity.class);
                intent.putExtra(TConstants.IntentKey.HEALTH_DETAILS, item);
                startActivity(intent);
            }
        });
    }


}
