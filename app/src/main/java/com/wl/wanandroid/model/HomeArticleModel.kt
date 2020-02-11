package com.wl.wanandroid.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wl.wanandroid.bean.HomeArticleBean
import com.wl.wanandroid.bean.HomeArticleData
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class HomeArticleModel(dataResultListener: BaseDataResultListener<HomeArticleBean>) :
    BaseModel<HomeArticleBean>(dataResultListener) {


    fun getHomeArticles(
        page: String,
        initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, HomeArticleData>?,
        loadMoreCallback: PageKeyedDataSource.LoadCallback<Int, HomeArticleData>?,
        boundaryPageData: MutableLiveData<Boolean>
    ):HomeArticleBean?{
        var homeArticleBean:HomeArticleBean?=null
        getServerData(page,methodName = "getHomeArticles",callBack = object :OnSuccessCallback<HomeArticleBean>{
            override fun invoke(t: HomeArticleBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    if(t.data.over){
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSNOMORE)
                        boundaryPageData.postValue(true);
                    }else{
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                        dataResultListener.setResultData(t)
                        homeArticleBean = t
                        if (initDataCallBack != null) {
                            initDataCallBack?.onResult(t.data.datas, -1, 0);
                        } else {
                            loadMoreCallback?.onResult(t.data.datas, page.toInt());
                        }

                        boundaryPageData.postValue(false);
                    }
                }

            }

        })

        return homeArticleBean
    }

}