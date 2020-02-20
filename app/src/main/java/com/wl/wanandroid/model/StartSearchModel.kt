package com.wl.wanandroid.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wl.wanandroid.bean.SearchResultBean
import com.wl.wanandroid.bean.SearchResultItemData
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class StartSearchModel(dataResultListener: BaseDataResultListener<SearchResultBean>) :
    BaseModel<SearchResultBean>(dataResultListener) {

    fun startSearch(
        page: String,
        key: String,
        initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, SearchResultItemData>?,
        loadMoreCallback: PageKeyedDataSource.LoadCallback<Int, SearchResultItemData>?,
        boundaryPageData: MutableLiveData<Boolean>
    ){
        getServerData(page.toString(),key,methodName = "startSearch",callBack = object :OnSuccessCallback<SearchResultBean>{
            override fun invoke(t: SearchResultBean) {
                if(vertifyResultError(t.errorCode,t.errorMsg)){
                    if(t.data.over){
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSNOMORE)
                        boundaryPageData.postValue(true);
                    }else{
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                        dataResultListener.setResultData(t)
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
    }
}