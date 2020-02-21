package com.wl.wanandroid.model

import com.wl.wanandroid.bean.PublicNumberArticleBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import retrofit2.http.Path

class GetPublicNumberArticleModel(dataResultListener: BaseDataResultListener<PublicNumberArticleBean>) :
    BaseModel<PublicNumberArticleBean>(dataResultListener) {
    fun getPublicNumber(id: String,page:String){
        getServerData(id,page,methodName = "getPublicNumber",callBack = object:OnSuccessCallback<PublicNumberArticleBean>{
            override fun invoke(t: PublicNumberArticleBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })
    }

}