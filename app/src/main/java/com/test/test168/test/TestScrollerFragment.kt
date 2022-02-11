package com.test.test168.test

import androidx.viewpager2.widget.ViewPager2
import com.test.test168.R
import com.test.test168.base.BaseFragment
import com.xian.common.adapter.CommonAdapter
import com.xian.common.adapter.ViewHolder

class TestScrollerFragment : BaseFragment() {

    companion object {
        fun newInstance() = TestScrollerFragment()
    }

    override fun getRootViewId(): Int {
        return R.layout.layout_empty_test_scroller
    }

    override fun initViews() {
            findViewById<ViewPager2>(R.id.rv_test_scroll)?.adapter =
                    object : CommonAdapter<String>() {
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