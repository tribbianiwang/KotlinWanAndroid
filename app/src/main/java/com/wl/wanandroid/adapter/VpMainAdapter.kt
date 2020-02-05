package com.wl.wanandroid.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class VpMainAdapter : FragmentPagerAdapter {

    lateinit var  fragmentList:ArrayList<Fragment>


    constructor(supportFragmentManager: FragmentManager?, fragmentList: ArrayList<Fragment>) : super(supportFragmentManager){
            this.fragmentList = fragmentList;
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }




}