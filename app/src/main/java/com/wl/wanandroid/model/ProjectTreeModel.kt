package com.wl.wanandroid.model

import com.wl.wanandroid.bean.ProjectTreeBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener

class ProjectTreeModel(dataResultListener: BaseDataResultListener<ProjectTreeBean>) :
    BaseModel<ProjectTreeBean>(dataResultListener) {

    fun getSystemTrees(){
        getServerData(AppConstants.NO_PARAMS,methodName = "getSystemTrees",callBack = object:OnSuccessCallback<ProjectTreeBean>{
            override fun invoke(systemTreeBean: ProjectTreeBean) {
                if(vertifyResultError(systemTreeBean.errorCode,systemTreeBean.errorMsg)){
                    dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSSUCCESS)
                    dataResultListener.setResultData(systemTreeBean)
                }

            }

        })

    }

}