package com.test.test168.activity

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.EditText
import com.test.test168.R
import com.test.test168.base.BaseActivity

class UriActivity : BaseActivity() {

    override fun initViews() {

        setContentView(R.layout.activity_uri)

        findViewById<View>(R.id.btn_change).setOnClickListener {
            findViewById<EditText>(R.id.et_uri_string).setText(
                schemeToIntentUriString(
                    findViewById<EditText>(
                        R.id.et_scheme
                    ).text.toString()
                )
            )
        }

    }

    private fun schemeToIntentUriString(text: String): String {
        val uri = Uri.parse(text)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val u = intent.toUri(Intent.URI_INTENT_SCHEME)
        return u
    }

}