package com.test.test168.juhe

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

import com.test.test168.R
import com.test.test168.TConstants
import com.test.test168.adapter.JuheHealthNewsClassListAdapter
import com.test.test168.base.BaseActivity
import com.test.test168.bean.JuheHealthNewsClass
import com.test.test168.bean.JuheHealthNewsClassList
import com.test.test168.bean.JuheHealthNewsClassListItem
import com.xian.common.utils.XLog


class HealthNewsClassListActivity : BaseActivity() {
    internal var title: TextView? = null
    internal var rvNewsClass: RecyclerView? = null

    internal var newsClass: JuheHealthNewsClass? = null
    internal var mLoader: HealthNewsClassListLoader? = null

    override fun initViews() {
        setContentView(R.layout.activity_health_news_details_list)
        title = findViewById(R.id.include_head_title)
        rvNewsClass = findViewById(R.id.rv_news_class_list)
        newsClass = intent.getSerializableExtra(TConstants.IntentKey.HEALTH_DETAILS) as JuheHealthNewsClass
        if (newsClass != null)
            setViews(newsClass!!)
    }

    private fun setViews(newsClass: JuheHealthNewsClass) {
        title?.text = newsClass.title
        onRequest(newsClass)
    }

    fun onRequest(newsClass: JuheHealthNewsClass) {

        mLoader = HealthNewsClassListLoader(mActivity)
        showLoadingDialog()
        mLoader?.loader(newsClass,
                object : CustomJuheSub<JuheHealthNewsClassList<JuheHealthNewsClassListItem>>() {
                    override fun onSuccess(result: JuheHealthNewsClassList<JuheHealthNewsClassListItem>) {
                        XLog.i("on success : " + result)
                        setListView(result.list)
                        dismissLoadingDialog()
                    }

                    override fun onFailure(errorMsg: String) {
                        XLog.e(errorMsg)
                        dismissLoadingDialog()
                    }
                })
    }

    private fun setListView(result: List<JuheHealthNewsClassListItem>) {
        rvNewsClass?.layoutManager = LinearLayoutManager(mActivity)
        rvNewsClass?.adapter = object : JuheHealthNewsClassListAdapter(mActivity, result) {
            override fun onItemClick(item: JuheHealthNewsClassListItem, position: Int) {
                //                XLog.i(" item : " + list);
                val intent = Intent(mActivity, HealthNewsDetailsActivity::class.java)
                intent.putExtra(TConstants.IntentKey.HEALTH_DETAILS, item)
                startActivity(intent)
            }
        }
    }
}
