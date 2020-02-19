package com.wl.wanandroid.model

import com.wl.wanandroid.bean.SearchResultBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class StartSearchModel(dataResultListener: BaseDataResultListener<SearchResultBean>) :
    BaseModel<SearchResultBean>(dataResultListener) {

    fun startSearch(page:Int,key:String){
        getServerData(page.toString(),key,methodName = "startSearch",callBack = object :OnSuccessCallback<SearchResultBean>{
            override fun invoke(t: SearchResultBean) {
            if(vertifyResultError(t.errorCode,t.errorMsg)){
                dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                dataResultListener.setResultData(t)
            }

            }


        })
    }
}