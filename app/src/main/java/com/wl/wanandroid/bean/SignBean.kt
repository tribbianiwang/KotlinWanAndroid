package com.wl.wanandroid.bean

data class SignBean(
    val `data`: SignData,
    val errorCode: Int,
    val errorMsg: String
)

data class SignData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
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