package com.wl.wanandroid.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tencent.mmkv.MMKV
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.SignBean
import com.wl.wanandroid.model.SignModel
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.ImmerBarUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.utils.T
import com.wl.wanandroid.viewmodel.SignViewModel
import kotlinx.android.synthetic.main.activity_public_number.index_toolbar
import kotlinx.android.synthetic.main.activity_public_number.tv_title
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : BaseActivity() {

    lateinit var signViewModel: SignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        ImmerBarUtils.initImmerBar(this,R.color.alpha_gray_white)

        initToolbar()

        signViewModel = ViewModelProviders.of(this).get(SignViewModel::class.java)

        lifecycle.addObserver(signViewModel)

        var signObserver = Observer<SignBean>{
            T.showShort(SignActivity@this,it.data.nickname+StringUtils.getString(R.string.string_sign_success))
            MMKV.defaultMMKV().encode(AppConstants.SIGN_USER_NAME,et_username.text.toString())
            finish()
        }


        signViewModel.baseResultLiveData.observe(this,signObserver)
        signViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        signViewModel.errorMsgLiveData.observe(this, errorMsgObserver)




        tv_sign.setOnClickListener {
            if(TextUtils.isEmpty(et_username.text)){
                StringUtils.setEdittextError(
                    et_username,
                    StringUtils.getString(R.string.string_username_empty)
                )
            }else if(TextUtils.isEmpty(et_password.text)){
                StringUtils.setEdittextError(
                    et_password,
                    StringUtils.getString(R.string.string_password_empty)
                )
            }else if(TextUtils.isEmpty(et_password_repeat.text)){
                StringUtils.setEdittextError(
                    et_password_repeat,
                    StringUtils.getString(R.string.et_password_repeat)
                )
            }else if(!(et_password.text.toString().equals(et_password_repeat.text.toString()))){
                StringUtils.setEdittextError(
                    et_password_repeat,
                    StringUtils.getString(R.string.string_password_not_equal)
                )
            }else{

                signViewModel.startSign(et_username.text.toString(),et_password.text.toString(),et_password_repeat.text.toString())


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

        tv_title.text = StringUtils.getString(R.string.string_sign)
    }
}
