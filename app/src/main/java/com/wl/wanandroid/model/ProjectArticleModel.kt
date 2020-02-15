package com.wl.wanandroid.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wl.wanandroid.bean.ProjectArticleBean
import com.wl.wanandroid.bean.ProjectArticleData
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class ProjectArticleModel(dataResultListener: BaseDataResultListener<ProjectArticleBean>) :
    BaseModel<ProjectArticleBean>(dataResultListener) {

    fun getProjectArticles(page:Int, projectTreeId:Int,
                           initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, ProjectArticleData>?,
                           loadMoreCallback: PageKeyedDataSource.LoadCallback<Int, ProjectArticleData>?,
                           boundaryPageData: MutableLiveData<Boolean>
    ){
        getServerData(page.toString(),projectTreeId.toString(),methodName = "getProjectArticles",callBack = object:OnSuccessCallback<ProjectArticleBean>{
            override fun invoke(t: ProjectArticleBean) {
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

        })

    }

}