package com.test.test168.juhe

import androidx.appcompat.widget.Toolbar
import android.text.Html
import android.widget.TextView

import com.test.test168.R
import com.test.test168.TConstants
import com.test.test168.base.BaseActivity
import com.test.test168.bean.JuheHealthNewsClassListItem
import com.test.test168.bean.JuheHealthNewsDetails
import com.xian.common.utils.XLog


class HealthNewsDetailsActivity : BaseActivity() {

    internal var newsClass: JuheHealthNewsClassListItem? = null
    internal var mLoader: HealthNewsDetailsLoader? = null

    internal var detailToolbar: Toolbar? = null
    internal var tvNewsDetailsTime: TextView? = null
    internal var tvNewsDetailsKeyword: TextView? = null
    internal var tvNewsDetailsReadCount: TextView? = null
    internal var tvNewsDetailsContent: TextView? = null

    override fun initViews() {
        setContentView(R.layout.activity_health_news_details)

        detailToolbar = findViewById(R.id.detail_toolbar)
        tvNewsDetailsTime = findViewById(R.id.tv_news_details_time)
        tvNewsDetailsKeyword = findViewById(R.id.tv_news_details_keyword)
        tvNewsDetailsReadCount = findViewById(R.id.tv_news_details_read_count)
        tvNewsDetailsContent = findViewById(R.id.tv_news_details_content)

        newsClass = intent.getSerializableExtra(TConstants.IntentKey.HEALTH_DETAILS) as JuheHealthNewsClassListItem
        if (newsClass != null)
            setViews(newsClass!!)
    }

    private fun setViews(newsClass: JuheHealthNewsClassListItem) {
        showLoadingDialog()
        detailToolbar!!.title = newsClass.title
        mLoader = HealthNewsDetailsLoader(mActivity)
        mLoader?.loader(newsClass,
                object : CustomJuheSub<JuheHealthNewsDetails>() {
                    override fun onSuccess(result: JuheHealthNewsDetails) {
                        XLog.i("on success : " + result)
                        setViewDetails(result)
                        dismissLoadingDialog()
                    }

                    override fun onFailure(errorMsg: String) {
                        XLog.e(errorMsg)
                        dismissLoadingDialog()
                    }
                })
    }

    private fun setViewDetails(result: JuheHealthNewsDetails) {
        tvNewsDetailsTime!!.text = "时间：" + java.sql.Date(result.time).toString()
        tvNewsDetailsKeyword!!.text = "关键字：" + result.keywords
        tvNewsDetailsReadCount!!.text = "阅读次数：" + result.count
        tvNewsDetailsContent!!.text = Html.fromHtml(result.message)
    }
}
