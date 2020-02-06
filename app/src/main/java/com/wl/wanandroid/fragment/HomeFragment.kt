package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tmall.ultraviewpager.UltraViewPager
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.PagerHomeBannerAdapter
import com.wl.wanandroid.bean.BannerBean
import com.wl.wanandroid.bean.Data
import com.wl.wanandroid.utils.AppConstants.HOME_BANNER_LOOP_TIME
import com.wl.wanandroid.viewmodel.BannerViewModel
import kotlinx.android.synthetic.main.layout_fragment_home.*


class HomeFragment : BaseFragment() {

    lateinit var bannerViewModel : BannerViewModel
    var bannerDatas:List<Data>?=null

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
            ulBanner?.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
            if (it.data.size > 1) {
                ulBanner?.setInfiniteLoop(true)
                ulBanner.setAutoScroll(HOME_BANNER_LOOP_TIME)
            } else {
                ulBanner.setInfiniteLoop(false)
            }

            ulBanner?.adapter = PagerHomeBannerAdapter(it.data)


        }


        bannerViewModel.baseResultLiveData.observe( this,bannerBeanObserver)
        bannerViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        bannerViewModel.errorMsgLiveData.observe(this,errorMsgObserver)

        bannerViewModel.getBannerData()
    }


}