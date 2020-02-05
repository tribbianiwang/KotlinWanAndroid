package com.wl.wanandroid.model

import android.text.TextUtils

import com.wl.wanandroid.R
import com.wl.wanandroid.retrofit.GetRetrofitService
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.BaseDataResultListener
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.utils.StringUtils
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.reflect.Method

open class BaseModel<T>(dataResultListener: BaseDataResultListener<T>){


    var dataResultListener:BaseDataResultListener<T>
    init {
        this.dataResultListener = dataResultListener
    }

      var baseObserver:Observable<T>?=null
      var baseSubscription:Subscription?=null



    fun onDestory(){

            baseObserver?.unsubscribeOn(Schedulers.io())
            baseSubscription?.unsubscribe()


    }


    interface OnSuccessCallback<T>{

        operator fun invoke(t: T)
    }

    fun getServerData(vararg params: String,methodName:String,callBack:OnSuccessCallback<T>){

        LogUtils.d("getServerData","getServerData")
        val commonParams = ArrayList<String>()
        dataResultListener?.setQueryStatus(AppConstants.QUERYSTATUSLOADING)
        commonParams.addAll(params.asList())

        val methods = GetRetrofitService.retrofitService.javaClass.methods
        var selectMethod:Method?=null
        for(method in methods){
            LogUtils.d("getServerData","methodName:${method.name}--${methodName}")
            if(methodName.equals(method.name)){
                selectMethod = method
                break;
            }
        }

        LogUtils.d("getServerData","selectmethodName:${selectMethod}")


        baseObserver= selectMethod?.let { invokeMethodInBaseModel(it,commonParams) }

       baseSubscription= baseObserver?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object:Subscriber<T>(){
                    override fun onNext(t: T) {
                        callBack.invoke(t)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable) {
                        dataResultListener.setQueryStatus(AppConstants.QUERYSTATUSFAILED)
                       dataResultListener.setErrorMsg(e.message.toString())
                    }
                })
    }


    fun vertifyResultError(isResult:Int,errorInfo:String?):Boolean{
        LogUtils.d("vertifyError","isResult:${isResult}")

      if(isResult!=AppConstants.ERRORCODESUCCESS){
            dataResultListener?.setQueryStatus(AppConstants.QUERYSTATUSFAILED)
            dataResultListener?.setErrorMsg(if(!TextUtils.isEmpty(errorInfo)) errorInfo!! else StringUtils.getString(R.string.string_normal_net_error))
            return false
        }else{
            return true
        }
    }

    private fun invokeMethodInBaseModel(selectMethod:Method,commonParams: MutableList<String>): Observable<T>? {

        LogUtils.d("invokmethodin","method:${selectMethod},paramsSize:${commonParams.size}")


       when(commonParams.size){

            1-> {
                if(AppConstants.NO_PARAMS.equals(commonParams[0])){
                    return  selectMethod?.invoke(GetRetrofitService.retrofitService) as Observable<T>
                }else{
                    return  selectMethod?.invoke(GetRetrofitService.retrofitService,commonParams[0]) as Observable<T>
                }
            }
            2-> return  selectMethod?.invoke(GetRetrofitService.retrofitService,commonParams[0],commonParams[1]) as Observable<T>
           3-> return  selectMethod?.invoke(GetRetrofitService.retrofitService,commonParams[0],commonParams[1],commonParams[2]) as Observable<T>
           4-> return  selectMethod?.invoke(GetRetrofitService.retrofitService,commonParams[0],commonParams[1],commonParams[2],commonParams[3]) as Observable<T>
           5-> return  selectMethod?.invoke(GetRetrofitService.retrofitService,commonParams[0],commonParams[1],commonParams[2],commonParams[3],commonParams[4]) as Observable<T>
           6-> return  selectMethod?.invoke(GetRetrofitService.retrofitService,commonParams[0],commonParams[1],commonParams[2],commonParams[3],commonParams[4],commonParams[5]) as Observable<T>

       }

        return null

    }

}