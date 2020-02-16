package com.wl.wanandroid.bean

data class SystemTreeBean(
    val `data`: List<SystemTreeData>,
    val errorCode: Int,
    val errorMsg: String
)

data class SystemTreeData(
    val children: List<SystemChildData>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class SystemChildData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)