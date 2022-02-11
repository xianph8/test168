package com.test.test168.motionlayout.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.test.test168.R
import com.test.test168.base.BaseFragment
import com.test.test168.test.TestScrollerFragment

class MotionLayoutFragment : BaseFragment() {

    companion object {
        fun newInstance() = MotionLayoutFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun getRootViewId(): Int {
        return R.layout.motion_layout_fragment
    }

    override fun initViews() {
        findViewById<ViewPager2>(R.id.vp2)?.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount(): Int {
                return 5
            }

            override fun createFragment(position: Int): Fragment {
                return TestScrollerFragment.newInstance()
            }
        }



    }

}