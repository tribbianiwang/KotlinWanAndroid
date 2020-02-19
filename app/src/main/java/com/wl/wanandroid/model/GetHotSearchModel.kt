package com.wl.wanandroid.model

import com.wl.wanandroid.bean.HotSearchBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class GetHotSearchModel(dataResultListener: BaseDataResultListener<HotSearchBean>) :
    BaseModel<HotSearchBean>(dataResultListener) {

    fun getHotSearch(){

        getServerData(AppConstants.NO_PARAMS,methodName ="getHotSearch",callBack = object :OnSuccessCallback<HotSearchBean>{
            override fun invoke(t: HotSearchBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })
    }
}