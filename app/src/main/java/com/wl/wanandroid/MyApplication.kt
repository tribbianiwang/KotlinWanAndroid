package com.wl.wanandroid

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.wl.wanandroid.retrofit.GetRetrofitService
import com.wl.wanandroid.utils.LogUtils
import com.tencent.mmkv.MMKV
import androidx.core.content.ContextCompat.getSystemService
import com.wl.wanandroid.utils.T



class MyApplication: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        GetRetrofitService.init()

        val rootDir = MMKV.initialize(this)
        LogUtils.d("myapplications","oncreate")
    }

    companion object{
        var instance: MyApplication? = null
    }

}