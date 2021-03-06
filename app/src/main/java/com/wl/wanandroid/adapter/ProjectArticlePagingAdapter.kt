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
import com.wl.wanandroid.bean.ProjectArticleData
import com.wl.wanandroid.bean.PublicNumberArticleData
import com.wl.wanandroid.utils.ImageViewUtils
import kotlinx.android.synthetic.main.layout_item_rv_home_publicnumber.view.*
import kotlinx.android.synthetic.main.layout_item_rv_project_article.view.*

class ProjectArticlePagingAdapter :
    PagedListAdapter<ProjectArticleData, ProjectArticlePagingAdapter.ViewHolder>(diffCallback) {
    var onRvItemClickListener: OnRvItemClickListener? = null
    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<ProjectArticleData>() {
            override fun areItemsTheSame(oldItem: ProjectArticleData, newItem: ProjectArticleData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ProjectArticleData, newItem: ProjectArticleData): Boolean =
                oldItem == newItem
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_project_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = getItem(position)
        holder.itemView.tv_title.setText(bean?.title)
        holder.itemView.tv_author.text = bean?.author
        holder.itemView.tv_date.text = bean?.niceDate
        ImageViewUtils.showImage(holder.itemView.context,holder.itemView.iv_image,bean?.envelopePic,R.drawable.icon_placeholder,R.drawable.icon_placeholder)

        holder.itemView.setOnClickListener {
            onRvItemClickListener?.onItemClick(position)
        }
    }

    open fun getArticleItemBean(position:Int): ProjectArticleData {
        return getItem(position) as ProjectArticleData

    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}