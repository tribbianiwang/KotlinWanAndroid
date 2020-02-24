package com.wl.wanandroid.model

import com.wl.wanandroid.bean.LogoutBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class LogoutModel(dataResultListener: BaseDataResultListener<LogoutBean>) :
    BaseModel<LogoutBean>(dataResultListener) {

    fun logOut(){
        getServerData(AppConstants.NO_PARAMS,methodName = "logOut",callBack = object :OnSuccessCallback<LogoutBean>{
            override fun invoke(t: LogoutBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })
    }
}