package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.PublicNumberArticleBean
import com.wl.wanandroid.model.GetPublicNumberArticleModel

class GetPublicNumberArticleViweModel:BaseViewModel<PublicNumberArticleBean>() {

    var getPublicNumberArticleModel = GetPublicNumberArticleModel(this)

    init {
        baseModel = getPublicNumberArticleModel
    }

    fun getPublicNumber(id: String,page:String){
        getPublicNumberArticleModel.getPublicNumber(id,page)

    }
}