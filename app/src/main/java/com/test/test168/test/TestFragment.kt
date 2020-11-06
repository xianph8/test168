package com.test.test168.test

import com.test.test168.R
import com.test.test168.base.BaseFragment

class TestFragment : BaseFragment() {

    companion object {
        fun newInstance() = TestFragment()
    }

    override fun getRootViewId(): Int {
        return R.layout.layout_empty_test
    }

    override fun initViews() {

    }


}