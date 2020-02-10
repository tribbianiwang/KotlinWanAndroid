package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.tmall.ultraviewpager.UltraViewPager
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.PagerHomeBannerAdapter
import com.wl.wanandroid.adapter.RvHomePublicNumberAdapter
import com.wl.wanandroid.bean.BannerBean
import com.wl.wanandroid.bean.PublicNumberListBean
import com.wl.wanandroid.utils.AppConstants.HOME_BANNER_LOOP_TIME
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.BannerViewModel
import com.wl.wanandroid.viewmodel.PublicNumberListViewModel
import kotlinx.android.synthetic.main.layout_fragment_home.*


class HomeFragment : BaseFragment() {

    lateinit var bannerViewModel : BannerViewModel
    lateinit var publicNumberListModel:PublicNumberListViewModel

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


        rv_publicnumber.layoutManager = GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
        hIndicator.bindRecyclerView(rv_publicnumber)


        bannerViewModel = ViewModelProviders.of(this).get(BannerViewModel::class.java)
        publicNumberListModel = ViewModelProviders.of(this).get(PublicNumberListViewModel::class.java)

        lifecycle.addObserver(bannerViewModel)
        lifecycle.addObserver(publicNumberListModel)

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

        var publicNumberObserver = Observer<PublicNumberListBean>{
            var rvHomePublicNumberAdapter = RvHomePublicNumberAdapter(it.data)
            rv_publicnumber.adapter = rvHomePublicNumberAdapter

        }


        bannerViewModel.baseResultLiveData.observe( this,bannerBeanObserver)
        bannerViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        bannerViewModel.errorMsgLiveData.observe(this,errorMsgObserver)
        publicNumberListModel.baseResultLiveData.observe(this,publicNumberObserver)
        publicNumberListModel.queryStatusLiveData.observe(this,queryStatusObserver)
        publicNumberListModel.errorMsgLiveData.observe(this,errorMsgObserver)



        bannerViewModel.getBannerData()
        publicNumberListModel.getPublicNumberList()
    }


}