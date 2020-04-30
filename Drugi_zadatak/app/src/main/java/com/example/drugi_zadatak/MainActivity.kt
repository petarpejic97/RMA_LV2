package com.example.drugi_zadatak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextUtils
import android.util.Log


class MainActivity : AppCompatActivity() {
    private var mOnButtonClicked: OnButtonClicked? = null
    private var onLongTouch : OnLongTouch ?= null
    private var updataId : Int = 0

    interface OnButtonClicked {
        fun refresh()
    }
    fun setOnButtonClicked(c: OnButtonClicked) {
        Log.w("USAO","USAO 1")
        mOnButtonClicked = c
        c.refresh()

    }
    fun refreshData() {
        Log.w("USAO","USAO 2")
        mOnButtonClicked!!.refresh()
        viewPager.currentItem=0
    }

    interface OnLongTouch{
        fun setFieldsForUpdate(id : Int)
    }

    fun setOnUpdating(c : OnLongTouch){
        onLongTouch = c
        c.setFieldsForUpdate(updataId)
    }

    fun getUpdataId(id : Int){
        onLongTouch?.setFieldsForUpdate(id)
        viewPager.currentItem=1
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
