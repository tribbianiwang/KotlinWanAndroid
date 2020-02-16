package com.wl.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.SystemChildData
import kotlinx.android.synthetic.main.layout_item_rv_system_children.view.*

class RvSystemChildAdapter(systemChildenData: ArrayList<SystemChildData>) :RecyclerView.Adapter<RvSystemChildAdapter.ViewHolder>(){

    var systemChildenData: ArrayList<SystemChildData>
    init {
        this.systemChildenData = systemChildenData
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var contentView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_system_children,parent,false)
        return ViewHolder(contentView)

    }

    override fun getItemCount(): Int {

       return systemChildenData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_system_child_name.text = systemChildenData.get(position).name

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}