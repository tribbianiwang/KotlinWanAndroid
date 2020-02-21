package com.wl.wanandroid.adapter

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tudaritest.util.DensityUtils
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.PublicNumberListBeanData
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.utils.ScreenUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.utils.StringUtils.getRandomColor
import kotlinx.android.synthetic.main.layout_item_rv_home_publicnumber.view.*

class RvHomePublicNumberAdapter(publicNumberData: List<PublicNumberListBeanData>) :RecyclerView.Adapter<RvHomePublicNumberAdapter.ViewHolder>() {
    var publicNumberData: List<PublicNumberListBeanData>

    var onRvItemClickListener:OnRvItemClickListener?=null

    init {
        this.publicNumberData = publicNumberData
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var publicNumberItemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_home_publicnumber,parent,false)

        return ViewHolder(publicNumberItemView)
    }

    override fun getItemCount(): Int {

        return publicNumberData.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_name_color.text = publicNumberData.get(position).name
        holder.itemView.tv_name.text = publicNumberData.get(position).name
        val layoutParams = holder.itemView.ll_root.layoutParams
        layoutParams.width = (ScreenUtils.getScreenWidth(holder.itemView.context as Activity)) / 5;//
        holder.itemView.ll_root.setLayoutParams(layoutParams);
        holder.itemView.setOnClickListener {
            onRvItemClickListener?.onItemClick(position)
        }



//创建Drawable对象
        var drawable= GradientDrawable();
//设置背景色
        drawable.setColor(getRandomColor());
//设置shape形状
        drawable.setShape(GradientDrawable.OVAL);
//控件设置shape背景
        holder.itemView.tv_name_color.setBackground(drawable);



    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}