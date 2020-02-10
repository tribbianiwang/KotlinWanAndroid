package com.wl.wanandroid.bean

data class PublicNumberListBean(
    val `data`: List<PublicNumberListBeanData>,
    val errorCode: Int,
    val errorMsg: String
)

data class PublicNumberListBeanData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)