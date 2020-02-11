package com.wl.wanandroid.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wl.wanandroid.bean.HomeArticleData
import android.widget.TextView
import androidx.annotation.NonNull
import android.view.LayoutInflater
import com.wl.wanandroid.R
import kotlinx.android.synthetic.main.layout_item_rv_home_article.view.*


class HomeArticlePagingAdapter() :
    PagedListAdapter<HomeArticleData, HomeArticlePagingAdapter.ViewHolder>(diffCallback) {

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<HomeArticleData>() {
            override fun areItemsTheSame(oldItem: HomeArticleData, newItem: HomeArticleData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HomeArticleData, newItem: HomeArticleData): Boolean =
                oldItem == newItem
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_home_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = getItem(position)
        holder.itemView.tv_name.setText(bean?.title)
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
