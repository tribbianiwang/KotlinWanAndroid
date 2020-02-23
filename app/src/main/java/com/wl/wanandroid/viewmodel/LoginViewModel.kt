package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.LoginBean
import com.wl.wanandroid.model.LoginModel

class LoginViewModel:BaseViewModel<LoginBean>() {
    var loginModel = LoginModel(this)

    init {
        baseModel = loginModel
    }

    fun startLogin(username:String,password:String){
        loginModel.startLogin(username,password)

    }
}