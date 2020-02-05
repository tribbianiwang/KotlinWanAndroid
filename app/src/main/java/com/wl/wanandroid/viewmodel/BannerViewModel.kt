package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.BannerBean
import com.wl.wanandroid.model.BannerModel
import com.wl.wanandroid.model.BaseModel

class BannerViewModel:BaseViewModel<BannerBean>() {
    var bannerModel = BannerModel(this)

    init {
        baseModel = bannerModel
    }

    fun getBannerData(){
        bannerModel.getBannerData()

    }



}