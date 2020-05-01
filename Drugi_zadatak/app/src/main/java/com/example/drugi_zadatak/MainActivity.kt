package com.example.drugi_zadatak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextUtils
import android.util.Log
import com.example.drugi_zadatak.Fragments.OnClickAddListener
import com.example.drugi_zadatak.Fragments.OnLongTouchListener


class MainActivity : AppCompatActivity() {
    private lateinit var mOnButtonClicked: OnClickAddListener
    private var onLongTouch : OnLongTouchListener ?= null
    private var updataId : Int = 0

    fun setOnButtonClicked(c: OnClickAddListener) {
        Log.w("USAO","USAO 1")
        mOnButtonClicked = c
        c.refresh()

    }
    fun refreshData() {
        Log.w("USAO","USAO 2")
        mOnButtonClicked!!.refresh()
        viewPager.currentItem=0
    }


    fun setOnUpdating(c : OnLongTouchListener){
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
