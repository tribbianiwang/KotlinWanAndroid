package com.wl.wanandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmall.ultraviewpager.UltraViewPager
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.HomeArticlePagingAdapter
import com.wl.wanandroid.adapter.PagerHomeBannerAdapter
import com.wl.wanandroid.adapter.RvHomePublicNumberAdapter
import com.wl.wanandroid.bean.BannerBean
import com.wl.wanandroid.bean.HomeArticleBean
import com.wl.wanandroid.bean.PublicNumberListBean
import com.wl.wanandroid.utils.AppConstants.HOME_BANNER_LOOP_TIME
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.BannerViewModel
import com.wl.wanandroid.viewmodel.HomeArticleViewModel
import com.wl.wanandroid.viewmodel.PublicNumberListViewModel
import kotlinx.android.synthetic.main.layout_fragment_home.*
import androidx.paging.PagedList

import com.wl.wanandroid.bean.HomeArticleData
import kotlinx.android.synthetic.main.layout_home_rv_articles_header.view.*
import android.widget.LinearLayout
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.activity.PublicNumberActivity
import com.wl.wanandroid.activity.SearchActivity
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.T


class HomeFragment : BaseFragment() {

    lateinit var bannerViewModel : BannerViewModel
    lateinit var publicNumberListModel:PublicNumberListViewModel
    lateinit var homeArticleViewModel:HomeArticleViewModel
    lateinit  var homeArticlePagingAdapter: HomeArticlePagingAdapter

    var bannerBean:BannerBean?=null
    var publicNumberBean:PublicNumberListBean?=null

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





        rv_home_articles.layoutManager = LinearLayoutManager(context)
        homeArticlePagingAdapter = HomeArticlePagingAdapter()
        rv_home_articles.adapter = homeArticlePagingAdapter


        bannerViewModel = ViewModelProviders.of(this).get(BannerViewModel::class.java)
        publicNumberListModel = ViewModelProviders.of(this).get(PublicNumberListViewModel::class.java)
        homeArticleViewModel = ViewModelProviders.of(this).get(HomeArticleViewModel::class.java)


        lifecycle.addObserver(bannerViewModel)
        lifecycle.addObserver(publicNumberListModel)
        lifecycle.addObserver(homeArticleViewModel)

        var bannerBeanObserver = Observer<BannerBean>{
            bannerBean = it
            publicNumberListModel.getPublicNumberList()



        }

        var publicNumberObserver = Observer<PublicNumberListBean>{


            val mainRvContentHeader = View.inflate(context,R.layout.layout_home_rv_articles_header, null)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            mainRvContentHeader.layoutParams = layoutParams
            homeArticlePagingAdapter.setHeaderView(mainRvContentHeader);

            //设置公众号相关
            publicNumberBean = it

            mainRvContentHeader.rv_publicnumber.layoutManager = GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
            mainRvContentHeader.hIndicator.bindRecyclerView(mainRvContentHeader.rv_publicnumber)
            var rvHomePublicNumberAdapter = RvHomePublicNumberAdapter(it.data)
            mainRvContentHeader.rv_publicnumber.adapter = rvHomePublicNumberAdapter

            rvHomePublicNumberAdapter.onRvItemClickListener = object :OnRvItemClickListener{
                override fun onItemClick(position: Int) {
                    var intent = Intent(context,PublicNumberActivity::class.java)
                    LogUtils.d("homfragmenttrans","name:${it.data.get(position).name},id:${it.data.get(position).id}")
                    intent.putExtra(AppConstants.PUBLIC_NUMBER_NAME,it.data.get(position).name)
                    intent.putExtra(AppConstants.PUBLIC_NUMBER_ID,it.data.get(position).id)
                    startActivity(intent)
                }


            }


            //设置banner相关
            mainRvContentHeader.ulBanner?.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
            if (it.data.size > 1) {
                mainRvContentHeader.ulBanner?.setInfiniteLoop(true)
                mainRvContentHeader.ulBanner.setAutoScroll(HOME_BANNER_LOOP_TIME)
            } else {
                mainRvContentHeader.ulBanner.setInfiniteLoop(false)
            }

            mainRvContentHeader.ulBanner?.adapter = bannerBean?.let { PagerHomeBannerAdapter(it.data) }


            homeArticlePagingAdapter.mListener = object:OnRvItemClickListener{
                override fun onItemClick(position: Int) {

                    LogUtils.d("homeFragment,","onClickTitle:${homeArticlePagingAdapter.getItemBean(position)?.title}")
                }

            }


        }

        var homeArticlesObserver = Observer<HomeArticleBean> {
            LogUtils.d("homeArticles","size:${it.data.datas.size}")

        }


        bannerViewModel.baseResultLiveData.observe( this,bannerBeanObserver)
        bannerViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        bannerViewModel.errorMsgLiveData.observe(this,errorMsgObserver)
        publicNumberListModel.baseResultLiveData.observe(this,publicNumberObserver)
        publicNumberListModel.queryStatusLiveData.observe(this,queryStatusObserver)
        publicNumberListModel.errorMsgLiveData.observe(this,errorMsgObserver)
        homeArticleViewModel.baseResultLiveData.observe(this,homeArticlesObserver)
        homeArticleViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        homeArticleViewModel.errorMsgLiveData.observe(this,errorMsgObserver)




        homeArticleViewModel.getArticleLiveData().observe(this,
            Observer<PagedList<HomeArticleData>> { datasBeans ->
                homeArticlePagingAdapter.submitList(datasBeans)
                bannerViewModel.getBannerData()
            })

        homeArticleViewModel.getBoundaryPageData().observe(this,
            Observer<Boolean> { haData ->
                if (!haData) {
                }
            })

        et_search.setOnClickListener {

            startActivity(Intent(context,SearchActivity::class.java))

        }

    }


}