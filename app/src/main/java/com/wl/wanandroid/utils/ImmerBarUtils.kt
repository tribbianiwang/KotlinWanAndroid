package com.wl.wanandroid.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar

open class ImmerBarUtils {
    companion object{
        fun initImmerBar(activity: AppCompatActivity, statusBarColor:Int){
            ImmersionBar.with(activity).fitsSystemWindows(true).statusBarColor(statusBarColor).autoDarkModeEnable(true).init()
        }
        fun initImmerBar(fragment: Fragment, statusBarColor:Int){
            ImmersionBar.with(fragment).fitsSystemWindows(true).statusBarColor(statusBarColor).autoDarkModeEnable(true).init()
        }
    }
}