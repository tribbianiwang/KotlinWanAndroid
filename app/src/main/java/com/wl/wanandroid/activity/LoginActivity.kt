package com.wl.wanandroid.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.ImmerBarUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.utils.StringUtils.setEdittextError
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_public_number.*
import kotlinx.android.synthetic.main.activity_public_number.index_toolbar
import kotlinx.android.synthetic.main.activity_public_number.tv_title

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ImmerBarUtils.initImmerBar(this,R.color.alpha_gray_white)
        initToolbar()


        tv_login.setOnClickListener {
            if(TextUtils.isEmpty(et_username.text)){
                setEdittextError(et_username,StringUtils.getString(R.string.string_username_empty))
            }else if(TextUtils.isEmpty(et_password.text)){
                setEdittextError(et_password,StringUtils.getString(R.string.string_password_empty))
            }else{
                //开始登录
            }

        }
    }

    private fun initToolbar() {

        index_toolbar.setNavigationIcon(R.drawable.icon_back_gray)
        index_toolbar.setTitleTextColor(Color.WHITE)
        index_toolbar.setTitle("")
        index_toolbar.setNavigationOnClickListener {
            finish()
        }

        tv_title.text = StringUtils.getString(R.string.string_login)
    }
}
