package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.ProjectArticlePagingAdapter
import com.wl.wanandroid.bean.ProjectArticleData
import com.wl.wanandroid.utils.ActivityUtils
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.viewmodel.ProjectArticleViewModel
import kotlinx.android.synthetic.main.layout_system_fragment_child.*

class ProjectChildFragment:BaseFragment() {
     var systemChildId:Int ?=0
    lateinit var systemChildName:String
    lateinit  var projectArticleViewModel:ProjectArticleViewModel
    var rvProjectArticleAdapter:ProjectArticlePagingAdapter?=null


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
        rv_project_articles.layoutManager = LinearLayoutManager(context)
        rvProjectArticleAdapter = ProjectArticlePagingAdapter()
        rv_project_articles.adapter = rvProjectArticleAdapter
//
        projectArticleViewModel = ViewModelProviders.of(this).get(ProjectArticleViewModel::class.java)

        projectArticleViewModel.projectTreeId = (systemChildId?:-1)
//
        lifecycle.addObserver(projectArticleViewModel)
//
//        projectArticleViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
//        projectArticleViewModel.errorMsgLiveData.observe(this,errorMsgObserver)

        projectArticleViewModel.getArticleLiveData().observe(this,
            Observer<PagedList<ProjectArticleData>> { datasBeans -> rvProjectArticleAdapter?.submitList(datasBeans) })

        projectArticleViewModel.getBoundaryPageData().observe(this,
            Observer<Boolean> { haData ->
                if (!haData) {
//                    refreshLayout.finishLoadMore()
//                    refreshLayout.finishRefresh()
                }
            })

        rvProjectArticleAdapter?.onRvItemClickListener = object : OnRvItemClickListener {
            override fun onItemClick(position: Int) {

                context?.let {
                ActivityUtils.skipToArticle(it,rvProjectArticleAdapter?.getArticleItemBean(position)?.id?:0,
                    rvProjectArticleAdapter?.getArticleItemBean(position)?.title?:"",rvProjectArticleAdapter?.getArticleItemBean(position)?.link?:"")

                }
            }

        }
    }

}