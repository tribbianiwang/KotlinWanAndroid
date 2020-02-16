package com.wl.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.SystemTreeData
import com.wl.wanandroid.utils.StringUtils
import kotlinx.android.synthetic.main.layout_fragment_system.view.*
import kotlinx.android.synthetic.main.layout_item_rv_system_tree.view.*

class RvSystemTreeAdapter(systemTreeDatas: List<SystemTreeData>) :RecyclerView.Adapter<RvSystemTreeAdapter.ViewHolder>(){
    var systemTreeDatas: List<SystemTreeData>
    var rvItemClickListener:OnRvItemClickListener?=null
    var selectedPosition:Int = -1
    init {
        this.systemTreeDatas = systemTreeDatas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       var contentView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv_system_tree,parent,false)
        return ViewHolder(contentView)
    }

    override fun getItemCount(): Int {
        return systemTreeDatas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_tree_name.text = systemTreeDatas.get(position).name
        if(position==selectedPosition){
            holder.itemView.tv_tree_name.paint.isFakeBoldText = true
            holder.itemView.setBackgroundColor(StringUtils.getColor(R.color.white))

        }else{
            holder.itemView.tv_tree_name.paint.isFakeBoldText = false
            holder.itemView.setBackgroundColor(StringUtils.getColor(R.color.alpha_gray_white))
        }
        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            rvItemClickListener?.onItemClick(position)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}