package com.wl.wanandroid

import androidx.multidex.MultiDexApplication
import com.wl.wanandroid.retrofit.GetRetrofitService
import com.wl.wanandroid.utils.LogUtils
import com.tencent.mmkv.MMKV


class MyApplication: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        GetRetrofitService.init(applicationContext)

        val rootDir = MMKV.initialize(this)
        LogUtils.d("myapplications","oncreate")
    }

    companion object{
        var instance: MyApplication? = null
    }

}