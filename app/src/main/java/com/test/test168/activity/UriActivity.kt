package com.test.test168.activity

import android.content.Intent
import android.net.Uri
import com.test.test168.R
import com.test.test168.base.BaseActivity
import kotlinx.android.synthetic.main.activity_uri.*

class UriActivity : BaseActivity() {

    override fun initViews() {

        setContentView(R.layout.activity_uri)

        btn_change.setOnClickListener {
            et_uri_string.setText(schemeToIntentUriString(et_scheme.text.toString()))
        }

    }

    private fun schemeToIntentUriString(text: String): String {
        val uri = Uri.parse(text)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val u = intent.toUri(Intent.URI_INTENT_SCHEME)
        return u
    }

}