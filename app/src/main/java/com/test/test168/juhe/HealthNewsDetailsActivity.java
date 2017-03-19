package com.test.test168.juhe;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.TConstants;
import com.test.test168.base.BaseActivity;
import com.test.test168.bean.JuheHealthNewsClassListItem;
import com.test.test168.bean.JuheHealthNewsDetails;
import com.xian.common.utils.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthNewsDetailsActivity extends BaseActivity {

    JuheHealthNewsClassListItem newsClass;
    HealthNewsDetailsLoader mLoader;

    @BindView(R.id.detail_toolbar)
    Toolbar detailToolbar;
    @BindView(R.id.tv_news_details_time)
    TextView tvNewsDetailsTime;
    @BindView(R.id.tv_news_details_keyword)
    TextView tvNewsDetailsKeyword;
    @BindView(R.id.tv_news_details_read_count)
    TextView tvNewsDetailsReadCount;
    @BindView(R.id.tv_news_details_content)
    TextView tvNewsDetailsContent;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_health_news_details);
        ButterKnife.bind(this);
        newsClass = (JuheHealthNewsClassListItem) getIntent().getSerializableExtra(TConstants.IntentKey.HEALTH_DETAILS);
        if (newsClass != null)
            setViews(newsClass);
    }

    private void setViews(JuheHealthNewsClassListItem newsClass) {
        showLoadingDialog();
        detailToolbar.setTitle(newsClass.getTitle());
        mLoader = new HealthNewsDetailsLoader(mActivity);
        mLoader.loader(newsClass,
                new CustomJuheSub<JuheHealthNewsDetails>() {
                    @Override
                    protected void onSuccess(JuheHealthNewsDetails result) {
                        XLog.i("on success : " + result);
                        setViewDetails(result);
                        dismissLoadingDialog();
                    }

                    @Override
                    protected void onFailure(String errorMsg) {
                        XLog.e(errorMsg);
                        dismissLoadingDialog();
                    }
                });
    }

    private void setViewDetails(JuheHealthNewsDetails result) {
        tvNewsDetailsTime.setText("时间：" + new java.sql.Date(result.getTime()).toString());
        tvNewsDetailsKeyword.setText("关键字：" + result.getKeywords());
        tvNewsDetailsReadCount.setText("阅读次数：" + result.getCount());
        tvNewsDetailsContent.setText(Html.fromHtml(result.getMessage()));
    }
}
