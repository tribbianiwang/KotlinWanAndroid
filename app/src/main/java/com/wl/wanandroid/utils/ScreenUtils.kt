package com.wl.wanandroid.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager





open class ScreenUtils {

    companion object{
        open fun getScreenWidth(activity: Activity):Int {
            val windowManager = activity.getWindowManager()
            val defaultDisplay = windowManager.defaultDisplay
            return defaultDisplay.width
        }
    }

}