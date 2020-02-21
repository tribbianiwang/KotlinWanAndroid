package com.wl.wanandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.LogUtils

class PublicNumberActivity : BaseActivity() {

    var publicNumberName:String = ""
    var publicNumberId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_number)
        publicNumberName = intent.getStringExtra(AppConstants.PUBLIC_NUMBER_NAME)
        publicNumberId = intent.getIntExtra(AppConstants.PUBLIC_NUMBER_ID,0)



    }


}
