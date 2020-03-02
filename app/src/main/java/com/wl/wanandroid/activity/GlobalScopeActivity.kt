package com.wl.wanandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.LogUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GlobalScopeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_scope)

        GlobalScope.launch {
            delay(5000)
            LogUtils.d("gloabalScopeActivity","launching")
        }

    }
}
