package com.wl.wanandroid.model

import com.wl.wanandroid.bean.LoginBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import retrofit2.http.Query

class LoginModel(dataResultListener: BaseDataResultListener<LoginBean>) :
    BaseModel<LoginBean>(dataResultListener) {

    fun startLogin(username:String,password:String){
        getServerData(username,password,methodName = "startLogin",callBack = object:OnSuccessCallback<LoginBean>{
            override fun invoke(t: LoginBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })

    }
}