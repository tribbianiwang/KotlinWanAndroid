package com.wl.wanandroid.utils

interface BaseDataResultListener<T> {
    fun setQueryStatus(string:String)
    fun setErrorMsg(string:String)
    fun setResultData(resultData:T)
}