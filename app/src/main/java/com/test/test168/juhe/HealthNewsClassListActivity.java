package com.test.test168.juhe;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.TConstants;
import com.test.test168.adapter.JuheHealthNewsClassAdapter;
import com.test.test168.base.BaseActivity;
import com.test.test168.bean.JuheHealthNewsClass;
import com.test.test168.bean.JuheResultBean;
import com.test.test168.network.CustomJuheSub;
import com.test.test168.utils.XLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthNewsClassListActivity extends BaseActivity {
    @Bind(R.id.include_head_title)
    TextView title;
    @Bind(R.id.rv_news_class_list)
    RecyclerView rvNewsClass;

    JuheHealthNewsClass newsClass;
    HealthNewsDetailsListLoader mLoader;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_health_news_details_list);
        ButterKnife.bind(this);
        newsClass = (JuheHealthNewsClass) getIntent().getSerializableExtra(TConstants.IntentKey.HEALTH_DETAILS);
        if (newsClass != null)
            setViews(newsClass);
    }

    private void setViews(JuheHealthNewsClass newsClass) {
        title.setText(newsClass.getTitle());
        onRequest(newsClass);
    }

    public void onRequest(JuheHealthNewsClass newsClass) {

        mLoader = new HealthNewsDetailsListLoader(mActivity);

        mLoader.loader(newsClass,
                new CustomJuheSub<JuheResultBean<JuheHealthNewsClass>>() {
                    @Override
                    protected void onSuccess(JuheResultBean<JuheHealthNewsClass> result) {
                        XLog.i("on success : " + result);
                        setListView(result.getList().getTngou());
                    }

                    @Override
                    protected void onFailure(String errorMsg) {
                        XLog.e(errorMsg);
                    }
                });
        ;
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
