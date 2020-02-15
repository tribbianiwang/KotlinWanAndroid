package com.wl.wanandroid.adapter

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wl.wanandroid.fragment.ProjectChildFragment

class ProjectFragmentVpAdapter(fragments: ArrayList<ProjectChildFragment>, titles:ArrayList<String>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
   var fragments: ArrayList<ProjectChildFragment>
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