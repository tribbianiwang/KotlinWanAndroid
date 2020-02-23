package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.CollectArticleResultBean
import com.wl.wanandroid.model.StartCollectArticleModel

class StartCollectArticleViewModel:BaseViewModel<CollectArticleResultBean>() {
    var startCollectArticleModel = StartCollectArticleModel(this)
    init {
        baseModel = startCollectArticleModel
    }

    fun startCollectArticle(articleId:String){
        startCollectArticleModel.startCollectArticle(articleId)

    }
}