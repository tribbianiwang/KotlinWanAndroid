package com.wl.wanandroid.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wl.wanandroid.bean.PublicNumberArticleBean
import com.wl.wanandroid.bean.PublicNumberArticleData
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import com.wl.wanandroid.utils.LogUtils

class GetPublicNumberArticleModel(dataResultListener: BaseDataResultListener<PublicNumberArticleBean>) :
    BaseModel<PublicNumberArticleBean>(dataResultListener) {
    fun getPublicNumber(
        id: String,
        page: String,
        initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, PublicNumberArticleData>?,
        loadMoreCallback: PageKeyedDataSource.LoadCallback<Int, PublicNumberArticleData>?,
        boundaryPageData: MutableLiveData<Boolean>
    ){
        getServerData(id,page,methodName = "getPublicNumber",callBack = object:OnSuccessCallback<PublicNumberArticleBean>{
            override fun invoke(t: PublicNumberArticleBean) {
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