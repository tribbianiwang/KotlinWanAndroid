package com.wl.wanandroid.viewmodel


import androidx.lifecycle.*
import com.wl.wanandroid.model.BaseModel
import com.wl.wanandroid.utils.BaseDataResultListener

open class BaseViewModel<T> : ViewModel(), LifecycleObserver, BaseDataResultListener<T> {


    var queryStatusLiveData = MutableLiveData<String>()
    var errorMsgLiveData= MutableLiveData<String>()
    var baseResultLiveData= MutableLiveData<T>()
    var baseModel: BaseModel<T>?=null

    override fun setQueryStatus(string: String) {
        queryStatusLiveData.postValue(string)


    }



    override fun setErrorMsg(errorMsg: String) {
        errorMsgLiveData.value = errorMsg
    }

    override fun setResultData(resultData: T) {
        baseResultLiveData.value = resultData

    }





    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory(){
        baseModel?.onDestory()
    }


}