package com.wl.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.CollectArticleItemData
import com.wl.wanandroid.utils.StringUtils
import kotlinx.android.synthetic.main.layout_item_rv_project_article.view.tv_title
import kotlinx.android.synthetic.main.layout_item_rv_search_result.view.*

class CollectArticlesPagingAdapter :
    PagedListAdapter<CollectArticleItemData, CollectArticlesPagingAdapter.ViewHolder>(diffCallback) {
    var onRvItemClickListener: OnRvItemClickListener?=null
    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<CollectArticleItemData>() {
            override fun areItemsTheSame(oldItem: CollectArticleItemData, newItem: CollectArticleItemData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CollectArticleItemData, newItem: CollectArticleItemData): Boolean =
                oldItem == newItem
        }
    }

    open fun getArticleItemBean(position:Int): CollectArticleItemData {
        return getItem(position) as CollectArticleItemData

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = getItem(position)
        holder.itemView.tv_title.setText(StringUtils.stripHtml(bean?.title.toString()))

            holder.itemView.tv_super_chapter_name.visibility = View.GONE


        holder.itemView.tv_user_name.setText(bean?.chapterName)
        holder.itemView.tv_time.setText(bean?.niceDate)

        holder.itemView.setOnClickListener {
            onRvItemClickListener?.onItemClick(position)
        }
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}