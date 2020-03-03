package com.wl.wanandroid.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.wl.wanandroid.activity.ArticleDetailActivity

open class ActivityUtils{

    companion object{
        object  list{
            var list:ArrayList<Activity>?=null
        }
        fun skipToArticle(context: Context,articleId:Int,articleTitle:String,articleUrl:String ){
            var intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(AppConstants.ARTICLE_ID,articleId)
            intent.putExtra(AppConstants.ARTICLE_TITLE,articleTitle)
            intent.putExtra(AppConstants.ARTICLE_URL,articleUrl)
            context.startActivity(intent)
        }

        fun addActivity(activity: Activity){
            list.list?.add(activity)
        }


        fun removeActivity(activity: Activity){
            list.list?.remove(activity)
        }

        fun exitActivity(){
            list.list?.clear();
            System.exit(0);

        }
    }

}