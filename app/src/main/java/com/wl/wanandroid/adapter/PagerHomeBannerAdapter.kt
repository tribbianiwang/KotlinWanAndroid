package com.wl.wanandroid.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.wl.wanandroid.R
import com.wl.wanandroid.activity.ArticleDetailActivity
import com.wl.wanandroid.bean.BannerBeanData
import com.wl.wanandroid.utils.ActivityUtils
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.ImageViewUtils
import kotlinx.android.synthetic.main.item_layout_home_banner.view.*


class PagerHomeBannerAdapter(mInformationList: List<BannerBeanData>) : PagerAdapter(){

    var mInfoList:ArrayList<BannerBeanData>?= ArrayList()
    init {
        mInfoList = mInformationList as ArrayList<BannerBeanData>
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view==`object`
    }

    override fun getCount(): Int {
      return  mInfoList?.size?:0
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var contentView = View.inflate(container.context, R.layout.item_layout_home_banner,null)
//        contentView.ivBannerItem.setImageResource(mInfoList?.get(position)?:R.drawable)
        ImageViewUtils.showImage(container.context,contentView.ivBannerItem,mInfoList?.get(position)?.imagePath?:"",R.drawable.icon_placeholder,R.drawable.icon_placeholder)
        container.addView(contentView)
        contentView.setOnClickListener {

            mInfoList?.get(position)?.let { it1 -> dosomething(container.context, it1) }
        }




        return contentView
    }
    private fun dosomething(context: Context, item: BannerBeanData) {

        ActivityUtils.skipToArticle(context,item.id,item.title,item.url)


    }



}