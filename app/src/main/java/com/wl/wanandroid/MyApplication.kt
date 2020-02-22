package com.wl.wanandroid

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.wl.wanandroid.retrofit.GetRetrofitService
import com.wl.wanandroid.utils.LogUtils

class MyApplication: MultiDexApplication(){
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