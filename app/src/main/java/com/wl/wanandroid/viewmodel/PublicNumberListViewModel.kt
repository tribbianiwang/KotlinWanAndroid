package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.PublicNumberListBean
import com.wl.wanandroid.model.PublicNumberListModel

class PublicNumberListViewModel:BaseViewModel<PublicNumberListBean>() {
    var getPublicNumberListModel = PublicNumberListModel(this)

    init {
        baseModel = getPublicNumberListModel
    }

    fun  getPublicNumberList(){
        getPublicNumberListModel.getPublicNumberList()
    }
}