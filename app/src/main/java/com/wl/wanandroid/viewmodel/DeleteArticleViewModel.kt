package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.DeleteCollectBean
import com.wl.wanandroid.model.DeleteArticleModel

class DeleteArticleViewModel:BaseViewModel<DeleteCollectBean>() {
    var deleteArticleModel = DeleteArticleModel(this)
    init {
        baseModel = deleteArticleModel
    }

    fun deleteCollectArticle(collectId:String){
        deleteArticleModel.deleteCollectArticle(collectId)

    }
}