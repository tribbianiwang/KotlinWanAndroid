package com.wl.wanandroid.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.Data
import com.wl.wanandroid.utils.ImageViewUtils
import kotlinx.android.synthetic.main.item_layout_home_banner.view.*


class PagerHomeBannerAdapter(mInformationList: List<Data>) : PagerAdapter(){

    var mInfoList:ArrayList<Data>?= ArrayList()
    init {
        mInfoList = mInformationList as ArrayList<Data>
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
    private fun dosomething(context: Context, item: Any) {
        // ListView 监听动作
        val al = item as Data
//        if ("Y" == al.IsWeb) {
//            context.startActivity(getListIntent(context,al, ActivityDisplayForWeb::class.java))
//        } else {
//            context.startActivity(getListIntent(context,al, ActivitiesDisplay::class.java))
//        }

    }



}