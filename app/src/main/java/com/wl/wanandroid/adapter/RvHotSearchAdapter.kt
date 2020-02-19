package com.wl.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.iterator
import androidx.recyclerview.widget.RecyclerView
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.HotSearchData
import kotlinx.android.synthetic.main.layout_item_rv_hotsearch.view.*

class RvHotSearchAdapter(hotSearchData: List<HotSearchData>) :RecyclerView.Adapter<RvHotSearchAdapter.ViewHolder>(){
    var hotSearchData:List<HotSearchData>

    var onItemClickListener:OnRvItemClickListener?=null
    init {
        this.hotSearchData = hotSearchData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var contentView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_hotsearch,parent,false)
        return ViewHolder(contentView)


    }

    override fun getItemCount(): Int {

        return hotSearchData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.tv_key.text = hotSearchData.get(position).name

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}