package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.LogoutBean
import com.wl.wanandroid.model.LogoutModel

class LogOutViewModel:BaseViewModel<LogoutBean>() {
    var logoutModel = LogoutModel(this)
    init {
        baseModel = logoutModel
    }

    fun logOut(){
        logoutModel.logOut()
    }

}