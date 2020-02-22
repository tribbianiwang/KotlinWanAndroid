package com.wl.wanandroid.model

import com.wl.wanandroid.bean.SystemChildArticleBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import retrofit2.http.Path
import retrofit2.http.Query

class GetSystemChildArticlesModel(dataResultListener: BaseDataResultListener<SystemChildArticleBean>) :
    BaseModel<SystemChildArticleBean>(dataResultListener) {

    fun getSystemChildArticles(page:String,cid:String){
        getServerData(page,cid,methodName = "getSystemChildArticles",callBack = object:OnSuccessCallback<SystemChildArticleBean>{
            override fun invoke(t: SystemChildArticleBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })

    }
}