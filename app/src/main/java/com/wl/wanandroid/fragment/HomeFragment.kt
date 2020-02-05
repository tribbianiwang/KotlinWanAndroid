package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatViewInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.BannerBean
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.BannerViewModel
import com.youth.banner.Banner

class HomeFragment : BaseFragment() {

    lateinit var bannerViewModel : BannerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var contentView = inflater.inflate(R.layout.layout_fragment_home, container, false)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bannerViewModel = ViewModelProviders.of(this).get(BannerViewModel::class.java)

        lifecycle.addObserver(bannerViewModel)

        var bannerBeanObserver = Observer<BannerBean>{

         for(banner in it.data){
             LogUtils.d("homefragment",banner.title)
         }
        }


        bannerViewModel.baseResultLiveData.observe( this,bannerBeanObserver)
        bannerViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        bannerViewModel.errorMsgLiveData.observe(this,errorMsgObserver)

        bannerViewModel.getBannerData()
    }


}