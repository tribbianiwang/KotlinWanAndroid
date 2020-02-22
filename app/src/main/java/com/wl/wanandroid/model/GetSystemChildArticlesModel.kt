package com.wl.wanandroid.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wl.wanandroid.bean.SystemChildArticleBean
import com.wl.wanandroid.bean.SystemChildArticleItemData
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import com.wl.wanandroid.utils.LogUtils

class GetSystemChildArticlesModel(dataResultListener: BaseDataResultListener<SystemChildArticleBean>) :
    BaseModel<SystemChildArticleBean>(dataResultListener) {

    fun getSystemChildArticles(
        page: String,
        cid: String,
        initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, SystemChildArticleItemData>?,
        loadMoreCallback: PageKeyedDataSource.LoadCallback<Int, SystemChildArticleItemData>?,
        boundaryPageData: MutableLiveData<Boolean>
    ){
        getServerData(page,cid,methodName = "getSystemChildArticles",callBack = object:OnSuccessCallback<SystemChildArticleBean>{
            override fun invoke(t: SystemChildArticleBean) {
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