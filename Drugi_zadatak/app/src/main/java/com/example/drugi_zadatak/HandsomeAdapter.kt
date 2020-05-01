package com.example.drugi_zadatak

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.drugi_zadatak.Fragments.FragmentAddNew
import com.example.drugi_zadatak.Fragments.FragmentInspiringPerson
class HandsomeAdapter(fragmentManager: androidx.fragment.app.FragmentManager) :  FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragments = arrayOf(
        FragmentInspiringPerson(),
        FragmentAddNew()
    )
    val titles = arrayOf("Inspiring person", "Add new ")
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
    override fun getCount(): Int {
        return fragments.size
    }

}
