package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.RvSystemChildAdapter
import com.wl.wanandroid.adapter.RvSystemTreeAdapter
import com.wl.wanandroid.bean.SystemChildData
import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.SystemTreeViewModel
import kotlinx.android.synthetic.main.layout_fragment_system.*

class SystemFragment : BaseFragment() {

    lateinit var systemTreeViewModel:SystemTreeViewModel
    var rvSystemTreeAdapter:RvSystemTreeAdapter?= null
    var systemChildenData:ArrayList<SystemChildData> = ArrayList()
    var rvSystemChildrenAdapter:RvSystemChildAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var contentView =inflater.inflate(R.layout.layout_fragment_system,container,false)

        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_system_tree.layoutManager = LinearLayoutManager(context)
        rv_system_children.layoutManager = GridLayoutManager(context,2)

        systemTreeViewModel = ViewModelProviders.of(this).get(SystemTreeViewModel::class.java)

        lifecycle.addObserver(systemTreeViewModel)

        var systemTreeObserver:Observer<SystemTreeBean> = Observer {
            LogUtils.d("systemTreeViewmodel","${it.data.size}")
            rvSystemTreeAdapter = RvSystemTreeAdapter(it.data)
            rv_system_tree.adapter = rvSystemTreeAdapter
            if(it.data!=null&&it.data.size>0){
                systemChildenData = it.data.get(0).children as ArrayList<SystemChildData>
                rvSystemChildrenAdapter = RvSystemChildAdapter(systemChildenData)
                rv_system_children.adapter = rvSystemChildrenAdapter
            }


        }

        systemTreeViewModel.baseResultLiveData.observe(this,systemTreeObserver)
        systemTreeViewModel.errorMsgLiveData.observe(this, errorMsgObserver)
        systemTreeViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        systemTreeViewModel.getSystemTree()


    }


}