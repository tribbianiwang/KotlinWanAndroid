package com.wl.wanandroid.utils
object AppConstants {

    val PUBLIC_NUMBER_NAME: String = "PUBLIC_NUMBER_NAME"
    val PUBLIC_NUMBER_ID: String = "PUBLIC_NUMBER_ID"

    val SYSTEM_NAME:String = "SYSTEM_NAME"
    val SYSTEM_ID:String = "SYSTEM_ID"

    val BASEROOTURL = "https://www.wanandroid.com/"

    const val BANNER_URL = "banner/json"//首页轮播图
    const val PUBLIC_NUMBER_LIST_URL = "wxarticle/chapters/json"//获取公众号列表
    const val PROJECT_TREE_URL = "project/tree/json"//项目分类

    const val  SYSTEM_TREE_URL = "tree/json"//体系分类
    const val HOT_SEARCH_URL = "hotkey/json"//热搜



    const val TRANS_SYSTEM_CHILD_ID = "TRANS_SYSTEM_CHILD_ID"
    const val TRANS_SYSTEM_CHILD_NAME = "TRANS_SYSTEM_CHILD_NAME"


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