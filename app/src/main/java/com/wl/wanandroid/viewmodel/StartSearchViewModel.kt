package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.SearchResultBean
import com.wl.wanandroid.model.StartSearchModel

class StartSearchViewModel:BaseViewModel<SearchResultBean>() {
    var startSearchModel = StartSearchModel(this)
    init {
        baseModel = startSearchModel
    }

    fun startSearch(page:Int,key:String){
        startSearchModel.startSearch(page,key)
    }
}