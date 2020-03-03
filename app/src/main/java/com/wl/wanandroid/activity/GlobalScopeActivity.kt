package com.wl.wanandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.LogUtils
import kotlinx.coroutines.*
import rx.schedulers.Schedulers.test

class GlobalScopeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_scope)

//        GlobalScope.launch {
//            delay(5000)
//            LogUtils.d("gloabalScopeActivity","launching")
//        }

//
//        LogUtils.d("gloabalScopeActivity","start${Thread.currentThread().name}")
//        GlobalScope.launch(Dispatchers.Main){
//            delay(5000)
//            LogUtils.d("gloabalScopeActivity","helloworld${Thread.currentThread().name}")
//        }
//        LogUtils.d("gloabalScopeActivity","end${Thread.currentThread().name}")

        suspend fun testIo(){
            withContext(Dispatchers.IO){
                LogUtils.d("gloabalScopeActivity","testIo${Thread.currentThread().name}")
            }

        }

        GlobalScope.launch(Dispatchers.Main){
            delay(5000)
            testIo()
            LogUtils.d("gloabalScopeActivity","inLaunch${Thread.currentThread().name}")
        }



    }
}
