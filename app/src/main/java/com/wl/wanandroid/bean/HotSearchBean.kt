package com.wl.wanandroid.bean

data class HotSearchBean(
    val `data`: List<HotSearchData>,
    val errorCode: Int,
    val errorMsg: String
)

data class HotSearchData(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)