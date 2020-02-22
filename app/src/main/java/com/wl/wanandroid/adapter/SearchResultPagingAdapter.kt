package com.wl.wanandroid.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.SearchResultItemData
import com.wl.wanandroid.utils.StringUtils
import kotlinx.android.synthetic.main.layout_item_rv_project_article.view.*
import kotlinx.android.synthetic.main.layout_item_rv_project_article.view.tv_title
import kotlinx.android.synthetic.main.layout_item_rv_search_result.view.*

class SearchResultPagingAdapter :
    PagedListAdapter<SearchResultItemData, SearchResultPagingAdapter.ViewHolder>(diffCallback) {

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<SearchResultItemData>() {
            override fun areItemsTheSame(oldItem: SearchResultItemData, newItem: SearchResultItemData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SearchResultItemData, newItem: SearchResultItemData): Boolean =
                oldItem == newItem
        }
    }

    var onRvItemClickListener:OnRvItemClickListener?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_search_result, parent, false)
        return ViewHolder(view)
    }

    open fun getSearchItemBean(position:Int):SearchResultItemData{
       return getItem(position) as SearchResultItemData

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = getItem(position)
        holder.itemView.tv_title.setText(StringUtils.stripHtml(bean?.title.toString()))
        if(TextUtils.isEmpty(bean?.superChapterName)){
            holder.itemView.tv_super_chapter_name.visibility = View.GONE
        }else{

            holder.itemView.tv_super_chapter_name.setText(bean?.superChapterName)
            holder.itemView.tv_super_chapter_name.visibility = View.VISIBLE
        }

        holder.itemView.tv_user_name.setText(bean?.chapterName)
        holder.itemView.tv_time.setText(bean?.niceShareDate)
        holder.itemView.setOnClickListener {
            onRvItemClickListener?.onItemClick(position)
        }
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}