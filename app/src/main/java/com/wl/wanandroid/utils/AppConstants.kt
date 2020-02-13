package com.wl.wanandroid.utils
object AppConstants {

    val BASEROOTURL = "https://www.wanandroid.com/"

    const val BANNER_URL = "banner/json"//首页轮播图
    const val PUBLIC_NUMBER_LIST_URL = "wxarticle/chapters/json"//获取公众号列表
    const val HOME_ARTICLE_URL = ""//首页文章列表bean




    const val QUERYSTATUSLOADING="QUERYSTATUSLOADING"
    const val QUERYSTATUSFAILED= "QUERYSTATUSFAILED"
    const val QUERYSTATUSSUCCESS="QUERYSTATUSSUCCESS"
    const val QUERYSTATUSEMPTY = "QUERYSTATUSEMPTY"
    const val QUERYSTATUSNOMORE = "QUERYSTATUSNOMORE"
    val NO_PARAMS = "NO_PARAMS"
    const val ERRORCODESUCCESS = 0

    const val USERLOGINBROADCAST = "USERLOGINBROADCAST";
    const val USERLOGOUTBROADCAST = "USERLOGOUTBROADCAST"
    const val UPDATEFOODBROADCAST = "UPDATEFOODBROADCAST"

    val TIMEOUT: Long = 10000
    val TOAST_DOUBLE_TIME_LIMIT = 300
    const val HOME_BANNER_LOOP_TIME = 4000
    val HIDE_LOADING_STATUS_MSG = "hide_loading_status_msg"
}