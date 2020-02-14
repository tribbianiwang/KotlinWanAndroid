package com.wl.wanandroid.model

import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class SyStemTreeModel(dataResultListener: BaseDataResultListener<SystemTreeBean>) :
    BaseModel<SystemTreeBean>(dataResultListener) {

    fun getSystemTrees(){
        getServerData(AppConstants.NO_PARAMS,methodName = "getSystemTrees",callBack = object:OnSuccessCallback<SystemTreeBean>{
            override fun invoke(systemTreeBean: SystemTreeBean) {
                if(vertifyResultError(systemTreeBean.errorCode,systemTreeBean.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(systemTreeBean)
                }

            }

        })

    }

}