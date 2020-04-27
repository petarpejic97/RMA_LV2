package com.example.drugi_zadatak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextUtils
import android.util.Log


class MainActivity : AppCompatActivity() {
    private var mOnButtonClicked: OnButtonClicked? = null
    private var mSubmitCache: String? = null

    interface OnButtonClicked {
        fun submit(s: String?)
    }

    fun setOnButtonClicked(c: OnButtonClicked) {
        mOnButtonClicked = c
        // deliver cached string, if any
        if (TextUtils.isEmpty(mSubmitCache) == false) {
            c.submit(mSubmitCache)
        }
    }
    fun buttonClicked(s: String) {
        Log.w("USAO",s)
        if (mOnButtonClicked == null) {
            // if FragmentB doesn't exist jet, cache value
            mSubmitCache = s
            return
        }
        mOnButtonClicked!!.submit(s)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }
    private fun setUpUi() {
        viewPager.adapter = HandsomeAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}
