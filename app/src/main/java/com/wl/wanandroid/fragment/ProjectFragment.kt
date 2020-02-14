package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatViewInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.SystemFragmentVpAdapter
import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.SystemTreeViewModel
import kotlinx.android.synthetic.main.layout_fragment_project.*

class ProjectFragment : BaseFragment() {
    
    lateinit var systemTreeViewModel:SystemTreeViewModel
    var systemFragmentVpAdapter:SystemFragmentVpAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var contentView =inflater.inflate(R.layout.layout_fragment_project,container,false)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        systemTreeViewModel = ViewModelProviders.of(this).get(SystemTreeViewModel::class.java)

        lifecycle.addObserver(systemTreeViewModel)

        var systemTreeObserver:Observer<SystemTreeBean> = object:Observer<SystemTreeBean>{
            override fun onChanged(t: SystemTreeBean?) {
                if(t?.data!=null&&t?.data.size>0){

                    systemFragmentVpAdapter = fragmentManager?.let { SystemFragmentVpAdapter(t.data, it) }
                    tablayout_system.setupWithViewPager(vp_system)
                    vp_system.adapter = systemFragmentVpAdapter


                }


            }

        }

        systemTreeViewModel.baseResultLiveData.observe(this,systemTreeObserver)
        systemTreeViewModel.errorMsgLiveData.observe(this, errorMsgObserver)
        systemTreeViewModel.queryStatusLiveData.observe(this,queryStatusObserver)

        systemTreeViewModel.getSystemTree()

    }
}