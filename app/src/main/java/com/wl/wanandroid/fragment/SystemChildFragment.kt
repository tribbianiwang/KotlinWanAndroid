package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.AppConstants
import kotlinx.android.synthetic.main.layout_system_fragment_child.*

class SystemChildFragment:BaseFragment() {
     var systemChildId:Int ?=0
    lateinit var systemChildName:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            var contentView =inflater.inflate(R.layout.layout_system_fragment_child,container,false)
            return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        systemChildId = arguments?.getInt(AppConstants.TRANS_SYSTEM_CHILD_ID)
        systemChildName =  arguments?.getString(AppConstants.TRANS_SYSTEM_CHILD_NAME).toString()
        tv_name.text = systemChildName+systemChildId
    }

}