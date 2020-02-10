package com.wl.wanandroid.model

import com.wl.wanandroid.bean.PublicNumberListBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class PublicNumberListModel(dataResultListener: BaseDataResultListener<PublicNumberListBean>) :
    BaseModel<PublicNumberListBean>(dataResultListener) {

    fun getPublicNumberList(){
        getServerData(AppConstants.NO_PARAMS,methodName = "getPublicNumberList",callBack =object: OnSuccessCallback<PublicNumberListBean>{
            override fun invoke(t: PublicNumberListBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)

                }

            }

        })

    }

}