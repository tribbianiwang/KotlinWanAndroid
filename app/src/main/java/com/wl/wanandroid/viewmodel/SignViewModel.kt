package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.SignBean
import com.wl.wanandroid.model.SignModel
import kotlin.math.sign

class SignViewModel:BaseViewModel<SignBean>() {
    var signModel = SignModel(this)

    init {
        baseModel = signModel
    }


    fun startSign(username: String,password:String,repassword:String){
        signModel.startSign(username,password,repassword)
    }


}