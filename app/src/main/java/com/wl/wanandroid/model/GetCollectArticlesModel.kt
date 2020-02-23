package com.wl.wanandroid.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wl.wanandroid.bean.CollectArticleBean
import com.wl.wanandroid.bean.CollectArticleItemData

import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import com.wl.wanandroid.utils.LogUtils

class GetCollectArticlesModel(dataResultListener: BaseDataResultListener<CollectArticleBean>) :
    BaseModel<CollectArticleBean>(dataResultListener) {

    fun getCollectArticles(
        page: String,
        initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, CollectArticleItemData>?,
        loadMoreCallback: PageKeyedDataSource.LoadCallback<Int, CollectArticleItemData>?,
        boundaryPageData: MutableLiveData<Boolean>
    ){
        getServerData(page,methodName = "getCollectArticles",callBack = object:OnSuccessCallback<CollectArticleBean>{
            override fun invoke(t: CollectArticleBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    if(t.data.over){
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSNOMORE)
                        boundaryPageData.postValue(true);
                    }else{
                        boundaryPageData.postValue(false);
                    }

                    if(t.data.datas.size>0){
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                        dataResultListener.setResultData(t)
                        if (initDataCallBack != null) {
                            initDataCallBack?.onResult(t.data.datas, -1, 0);
                        } else {
                            loadMoreCallback?.onResult(t.data.datas, page.toInt());
                        }
                    }else{
                        LogUtils.d("showempty","inmodel")
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSEMPTY)
                    }

                }


            }

        })

    }
}