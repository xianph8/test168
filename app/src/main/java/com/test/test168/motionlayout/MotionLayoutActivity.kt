package com.test.test168.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.test168.R
import com.test.test168.motionlayout.ui.main.MotionLayoutFragment

class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_layout_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MotionLayoutFragment.newInstance())
                    .commitNow()
        }
    }
}