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
import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.SystemTreeViewModel

class SystemFragment : BaseFragment() {

    lateinit var systemTreeViewModel:SystemTreeViewModel

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

        systemTreeViewModel = ViewModelProviders.of(this).get(SystemTreeViewModel::class.java)

        lifecycle.addObserver(systemTreeViewModel)

        var systemTreeObserver:Observer<SystemTreeBean> = Observer {
            LogUtils.d("systemTreeViewmodel","${it.data.size}")
        }

        systemTreeViewModel.baseResultLiveData.observe(this,systemTreeObserver)
        systemTreeViewModel.errorMsgLiveData.observe(this, errorMsgObserver)
        systemTreeViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        systemTreeViewModel.getSystemTree()
    }


}