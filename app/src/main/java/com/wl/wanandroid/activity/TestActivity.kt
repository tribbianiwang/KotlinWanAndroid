package com.wl.wanandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wl.wanandroid.R


import androidx.fragment.app.Fragment
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentManager
import com.wl.wanandroid.fragment.SystemFragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout





class TestActivity : AppCompatActivity() {

    val NUM_ITEMS = 4
    private val fragmentList = ArrayList<Fragment>()
    private val strings = arrayOf("A", "B", "C", "D")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wl.wanandroid.R.layout.activity_test)
        fragmentList.add(SystemFragment())
        fragmentList.add(SystemFragment())
        fragmentList.add(SystemFragment())
        fragmentList.add(SystemFragment())
        initView()
    }

    private fun initView() {
        val tab_layout = findViewById<TabLayout>(com.wl.wanandroid.R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(com.wl.wanandroid.R.id.viewPager)
        val fragmentAdater = MyAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdater
        tab_layout.setupWithViewPager(viewPager)
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return NUM_ITEMS
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence? {
            return strings[position]
        }
    }
}
