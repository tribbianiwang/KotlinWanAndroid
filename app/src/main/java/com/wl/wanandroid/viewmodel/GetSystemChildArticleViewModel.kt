package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.SystemChildArticleBean
import com.wl.wanandroid.model.GetSystemChildArticlesModel

class GetSystemChildArticleViewModel:BaseViewModel<SystemChildArticleBean>() {
    var cid:String=""

    var getSystemChildArticleModel = GetSystemChildArticlesModel(this)

    init {
        baseModel = getSystemChildArticleModel
    }

    fun getSystemChildArticles(page:String,cid:String){
        getSystemChildArticleModel.getSystemChildArticles(page,cid)

    }
}