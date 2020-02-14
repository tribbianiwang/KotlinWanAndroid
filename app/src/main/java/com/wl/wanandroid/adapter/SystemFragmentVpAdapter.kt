package com.wl.wanandroid.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wl.wanandroid.bean.SystemTreeData
import com.wl.wanandroid.fragment.SystemChildFragment
import com.wl.wanandroid.utils.AppConstants.TRANS_SYSTEM_CHILD_ID
import com.wl.wanandroid.utils.AppConstants.TRANS_SYSTEM_CHILD_NAME
import com.wl.wanandroid.utils.LogUtils

class SystemFragmentVpAdapter(systemTreeDatas: List<SystemTreeData>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    var systemTreeDatas: List<SystemTreeData>
    init {
        this.systemTreeDatas = systemTreeDatas

    }


    override fun getItem(position: Int): Fragment {
        var systemChildFragment = SystemChildFragment()
        var bundle = Bundle()
        bundle.putInt(TRANS_SYSTEM_CHILD_ID,systemTreeDatas.get(position).id)
        bundle.putString(TRANS_SYSTEM_CHILD_NAME,systemTreeDatas.get(position).name)
        systemChildFragment.arguments =bundle

        return  systemChildFragment
    }

    override fun getCount(): Int {

        return systemTreeDatas.size

    }
//
//    override fun getPageTitle(position: Int): CharSequence{
//        LogUtils.d("pageTitles:","position:${position}:name:${systemTreeDatas.get(position).name}")
//        return systemTreeDatas.get(position).name
////        return "1234324723894"
//
//    }

    override fun getPageTitle(position: Int): CharSequence? {
        return systemTreeDatas.get(position).name
    }


}