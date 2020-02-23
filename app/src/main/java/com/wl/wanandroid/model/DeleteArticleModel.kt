package com.wl.wanandroid.model

import com.wl.wanandroid.bean.DeleteCollectBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import retrofit2.http.Path

class DeleteArticleModel(dataResultListener: BaseDataResultListener<DeleteCollectBean>) :
    BaseModel<DeleteCollectBean>(dataResultListener) {

    fun deleteCollectArticle(collectId:String){
        getServerData(collectId,methodName = "deleteCollectArticle",callBack = object:OnSuccessCallback<DeleteCollectBean>{
            override fun invoke(t: DeleteCollectBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(t)
                }


            }

        })
    }

}