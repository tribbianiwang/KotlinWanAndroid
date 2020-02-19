package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.HotSearchBean
import com.wl.wanandroid.model.GetHotSearchModel

class GetHotSearchViewModel:BaseViewModel<HotSearchBean>(){
    var getHotSearchModel = GetHotSearchModel(this)
    init {
        baseModel= getHotSearchModel
    }

    fun getHotSearch(){
        getHotSearchModel.getHotSearch()
    }
}