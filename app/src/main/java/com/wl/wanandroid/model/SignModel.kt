package com.wl.wanandroid.model

import com.wl.wanandroid.bean.SignBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import retrofit2.http.Query

class SignModel(dataResultListener: BaseDataResultListener<SignBean>) :
    BaseModel<SignBean>(dataResultListener) {

    fun startSign(username: String,password:String,repassword:String){
        getServerData(username,password,repassword,methodName = "startSign",callBack = object:OnSuccessCallback<SignBean>{
            override fun invoke(t: SignBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })
    }
}