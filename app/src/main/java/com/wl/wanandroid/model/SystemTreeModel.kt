package com.wl.wanandroid.model

import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class SystemTreeModel(dataResultListener: BaseDataResultListener<SystemTreeBean>) :
    BaseModel<SystemTreeBean>(dataResultListener) {

    fun getSystemTree(){
        getServerData(AppConstants.NO_PARAMS,methodName = "getSystemTree",callBack = object :OnSuccessCallback<SystemTreeBean>{
            override fun invoke(t: SystemTreeBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })
    }
}