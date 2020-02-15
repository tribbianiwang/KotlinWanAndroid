package com.wl.wanandroid.retrofit

import com.wl.wanandroid.bean.*
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

    @GET("project/list/{page}/json")
    fun getProjectArticles(@Path("page")page:String,@Query("cid")projectTreeId:String):Observable<ProjectArticleBean>



}