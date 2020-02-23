package com.wl.wanandroid.bean

data class LoginBean(
    val `data`: LoginData,
    val errorCode: Int,
    val errorMsg: String
)

data class LoginData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)