package com.wl.wanandroid.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.bean.HomeArticleData
import kotlinx.android.synthetic.main.layout_item_rv_home_article.view.*
import com.wl.wanandroid.paging.AdapterDataObserverProxy



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

    val TYPE_HEADER = 0
    val TYPE_NORMAL = 1

     var mHeaderView: View? = null
     var onRvItemClickListener: OnRvItemClickListener? = null

    fun setHeaderView(headerView: View) {
        mHeaderView = headerView
        notifyItemInserted(0)
    }

    override fun getItemViewType(position: Int): Int {
        if (mHeaderView == null) return TYPE_NORMAL
        return if (position == 0) TYPE_HEADER else TYPE_NORMAL
    }
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mHeaderView != null && viewType === TYPE_HEADER) return ViewHolder(mHeaderView!!)
        val view = LayoutInflater.from(parent.context).inflate(com.wl.wanandroid.R.layout.layout_item_rv_home_article, parent, false)
        return ViewHolder(view)
    }

    fun getItemBean(position:Int):HomeArticleData?{
        return  getItem(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        var  pos = getRealPosition(holder);
        val bean = getItem(pos)
        holder.itemView.tv_title.setText(bean?.title)
        holder.itemView.tv_super_chapter_name.text = bean?.superChapterName
        holder.itemView.tv_user_name.text = bean?.shareUser
        holder.itemView.tv_time.text = bean?.niceShareDate

        if (holder is ViewHolder) {
            if (onRvItemClickListener == null) return
            holder.itemView.setOnClickListener(View.OnClickListener { onRvItemClickListener?.onItemClick(pos) })
        }
    }

    override fun getItemCount(): Int {
        return if (mHeaderView == null) super.getItemCount() else super.getItemCount() + 1
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun getRealPosition(holder: RecyclerView.ViewHolder): Int {
        val position = holder.layoutPosition
        return if (mHeaderView == null) position else position - 1
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {

        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, 1))
    }

}