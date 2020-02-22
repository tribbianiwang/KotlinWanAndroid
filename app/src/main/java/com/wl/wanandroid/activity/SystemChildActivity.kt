package com.wl.wanandroid.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.AppConstants
import kotlinx.android.synthetic.main.activity_public_number.*

class SystemChildActivity : AppCompatActivity() {

    var systemChildName:String = ""
    var systemChildId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_child)

        systemChildName = intent.getStringExtra(AppConstants.SYSTEM_NAME)
        systemChildId = intent.getIntExtra(AppConstants.SYSTEM_ID,0)

        initToolbar()
    }

    private fun initToolbar() {

        index_toolbar.setNavigationIcon(R.drawable.icon_back_gray)
        index_toolbar.setTitleTextColor(Color.WHITE)
        index_toolbar.setTitle("")
        index_toolbar.setNavigationOnClickListener {
            finish()
        }

        tv_title.text = systemChildName
    }
}
