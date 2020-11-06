package com.test.test168.test

import com.test.test168.R
import com.test.test168.base.BaseFragment
import com.xian.common.adapter.CommonAdapter
import com.xian.common.adapter.ViewHolder
import kotlinx.android.synthetic.main.layout_empty_test_scroller.*

class TestScrollerFragment : BaseFragment() {

    companion object {
        fun newInstance() = TestScrollerFragment()
    }

    override fun getRootViewId(): Int {
        return R.layout.layout_empty_test_scroller
    }

    override fun initViews() {
        rv_test_scroll.adapter = object : CommonAdapter<String>() {
            override fun getItemLayoutId(): Int {
                return R.layout.item_test_string
            }

            override fun onBindItem(holder: ViewHolder?, item: String) {
                holder?.setText(R.id.test_string, item)
            }
        }.apply {

            val list = listOf(
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1"
            )

            items = list

        }
    }


}