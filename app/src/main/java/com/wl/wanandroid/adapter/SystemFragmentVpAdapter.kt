package com.wl.wanandroid.adapter

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wl.wanandroid.bean.SystemTreeData
import com.wl.wanandroid.fragment.SystemChildFragment
import com.wl.wanandroid.utils.AppConstants.TRANS_SYSTEM_CHILD_ID
import com.wl.wanandroid.utils.AppConstants.TRANS_SYSTEM_CHILD_NAME
import com.wl.wanandroid.utils.LogUtils

class SystemFragmentVpAdapter(fragments: ArrayList<SystemChildFragment>,titles:ArrayList<String>,fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
   var fragments: ArrayList<SystemChildFragment>
   var titles:ArrayList<String>
    init {
        this.fragments = fragments
        this.titles = titles

    }


    override fun getItem(position: Int): Fragment {
       return fragments.get(position)
    }

    override fun getCount(): Int {

        return titles.size

    }
    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }



}