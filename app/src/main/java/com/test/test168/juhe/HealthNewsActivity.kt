package com.test.test168.juhe

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.test.test168.R
import com.test.test168.TConstants
import com.test.test168.adapter.JuheHealthNewsClassAdapter
import com.test.test168.api.JuheApi
import com.test.test168.base.BaseActivity
import com.test.test168.bean.JuheHealthNewsClass
import com.test.test168.bean.JuheResultBean
import com.xian.common.utils.XLog

import java.util.HashMap

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 聚合数据接口
 */
class HealthNewsActivity : BaseActivity() {

    internal var rvNewsClass: RecyclerView? = null

    override fun initViews() {
        setContentView(R.layout.activity_health_news)
        rvNewsClass = findViewById(R.id.rv_news_class)

        (`$`<View>(R.id.include_head_title) as TextView).text = "健康资讯"

        onRequest()
    }

    fun onRequest() {
        showLoadingDialog()
        val map = HashMap<String, String>()
        map.put("key", JuheApi.key)
        map.put("dtype", JuheApi.dtype)
        JuheApiWrapper.getInstance()
                .create(JuheApi::class.java)
                .getNewsClass(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CustomJuheSub<JuheResultBean<JuheHealthNewsClass>>() {
                    override fun onSuccess(result: JuheResultBean<JuheHealthNewsClass>) {
                        XLog.i("on success : " + result)
                        setListView(result.list.tList)
                        dismissLoadingDialog()
                    }

                    override fun onFailure(errorMsg: String) {
                        XLog.e(errorMsg)
                        dismissLoadingDialog()
                    }
                })
    }

    private fun setListView(result: List<JuheHealthNewsClass>) {
        rvNewsClass?.layoutManager = LinearLayoutManager(mActivity)
        rvNewsClass?.adapter = object : JuheHealthNewsClassAdapter(mActivity, result) {
            override fun onItemClick(item: JuheHealthNewsClass, position: Int) {
                //                XLog.i(" item : " + list);
                val intent = Intent(mActivity, HealthNewsClassListActivity::class.java)
                intent.putExtra(TConstants.IntentKey.HEALTH_DETAILS, item)
                startActivity(intent)
            }
        }
    }


}
