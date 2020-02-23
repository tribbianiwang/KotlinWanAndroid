package com.wl.wanandroid.bean

data class CollectArticleBean(
    val `data`: CollectArticleItemDatas,
    val errorCode: Int,
    val errorMsg: String
)

data class CollectArticleItemDatas(
    val curPage: Int,
    val datas: List<CollectArticleItemData>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class CollectArticleItemData(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val originId: Int,
    val publishTime: Long,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)