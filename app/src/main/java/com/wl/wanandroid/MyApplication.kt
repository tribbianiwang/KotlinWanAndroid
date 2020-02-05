package com.wl.wanandroid

import android.app.Application
import com.wl.wanandroid.retrofit.GetRetrofitService
import com.wl.wanandroid.utils.LogUtils

class MyApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        GetRetrofitService.init()

        LogUtils.d("myapplications","oncreate")
    }

    companion object{
        var instance: MyApplication? = null
    }

}