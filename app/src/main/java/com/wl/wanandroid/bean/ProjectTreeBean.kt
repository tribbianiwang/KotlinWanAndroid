package com.wl.wanandroid.bean

data class ProjectTreeBean(
    val `data`: List<ProjectTreeData>,
    val errorCode: Int,
    val errorMsg: String
)

data class ProjectTreeData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)