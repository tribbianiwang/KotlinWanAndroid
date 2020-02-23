package com.wl.wanandroid.model

import com.wl.wanandroid.bean.CollectArticleResultBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import retrofit2.http.Path

class StartCollectArticleModel(dataResultListener: BaseDataResultListener<CollectArticleResultBean>) :
    BaseModel<CollectArticleResultBean>(dataResultListener) {

    fun startCollectArticle(articleId:String){
        getServerData(articleId,methodName = "startCollectArticle",callBack = object :OnSuccessCallback<CollectArticleResultBean>{
            override fun invoke(t: CollectArticleResultBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }

            }

        })

    }

}