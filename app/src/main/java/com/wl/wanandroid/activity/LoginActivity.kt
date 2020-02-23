package com.wl.wanandroid.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.LoginBean
import com.wl.wanandroid.utils.ImmerBarUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.utils.StringUtils.setEdittextError
import com.wl.wanandroid.utils.T
import com.wl.wanandroid.viewmodel.BaseViewModel
import com.wl.wanandroid.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_public_number.*
import kotlinx.android.synthetic.main.activity_public_number.index_toolbar
import kotlinx.android.synthetic.main.activity_public_number.tv_title

class LoginActivity : BaseActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ImmerBarUtils.initImmerBar(this,R.color.alpha_gray_white)
        initToolbar()
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        lifecycle.addObserver(loginViewModel)

        var loginObserver = Observer<LoginBean>{
            T.showShort(LoginActivity@this,it.data.nickname)

        }

        loginViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        loginViewModel.errorMsgLiveData.observe(this,errorMsgObserver)
        loginViewModel.baseResultLiveData.observe(this,loginObserver)



        tv_login.setOnClickListener {
            if(TextUtils.isEmpty(et_username.text)){
                setEdittextError(et_username,StringUtils.getString(R.string.string_username_empty))
            }else if(TextUtils.isEmpty(et_password.text)){
                setEdittextError(et_password,StringUtils.getString(R.string.string_password_empty))
            }else{
                //开始登录
                loginViewModel.startLogin(et_username.text.toString(),et_password.text.toString())
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
