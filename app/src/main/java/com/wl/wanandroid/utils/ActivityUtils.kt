package com.wl.wanandroid.utils

import android.content.Context
import android.content.Intent
import com.wl.wanandroid.activity.ArticleDetailActivity

open class ActivityUtils{

    companion object{

        fun skipToArticle(context: Context,articleId:Int,articleTitle:String,articleUrl:String ){
            var intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(AppConstants.ARTICLE_ID,articleId)
            intent.putExtra(AppConstants.ARTICLE_TITLE,articleTitle)
            intent.putExtra(AppConstants.ARTICLE_URL,articleUrl)
            context.startActivity(intent)
        }
    }

}