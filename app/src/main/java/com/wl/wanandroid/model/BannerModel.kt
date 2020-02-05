package com.wl.wanandroid.model

import com.wl.wanandroid.bean.BannerBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import com.wl.wanandroid.utils.LogUtils

class BannerModel(dataResultListener: BaseDataResultListener<BannerBean>) :
    BaseModel<BannerBean>(dataResultListener) {

    fun getBannerData(){

        getServerData(AppConstants.NO_PARAMS,methodName ="getBannerData",callBack = object :OnSuccessCallback<BannerBean>{
            override fun invoke(t: BannerBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })

    }

}