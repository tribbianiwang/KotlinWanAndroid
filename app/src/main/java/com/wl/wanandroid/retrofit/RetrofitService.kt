package com.wl.wanandroid.retrofit

import com.wl.wanandroid.bean.BannerBean
import com.wl.wanandroid.bean.HomeArticleBean
import com.wl.wanandroid.bean.PublicNumberListBean
import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.AppConstants.SYSTEM_TREE_URL
import retrofit2.http.*
import rx.Observable


interface  RetrofitService{
    @GET(AppConstants.BANNER_URL)
    fun getBannerData():Observable<BannerBean>//获取banner

    @GET(AppConstants.PUBLIC_NUMBER_LIST_URL)
    fun getPublicNumberList():Observable<PublicNumberListBean>//获取首页公众号列表

    @GET("article/list/{page}/json")
    fun getHomeArticles(@Path("page")page: String):Observable<HomeArticleBean>//获取首页蚊帐列表

    @GET(SYSTEM_TREE_URL)
    fun getSystemTrees():Observable<SystemTreeBean> //获取项目类别



}