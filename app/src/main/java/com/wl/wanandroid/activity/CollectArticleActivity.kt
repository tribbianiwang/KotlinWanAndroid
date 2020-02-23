package com.wl.wanandroid.activity

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.CollectArticlesPagingAdapter
import com.wl.wanandroid.bean.CollectArticleItemData
import com.wl.wanandroid.bean.SystemChildArticleItemData
import com.wl.wanandroid.utils.ActivityUtils
import com.wl.wanandroid.utils.ImmerBarUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.viewmodel.GetCollectArticleViewModel
import kotlinx.android.synthetic.main.activity_collect_article.*

class CollectArticleActivity : BaseActivity() {

    var collectArticleAdapter: CollectArticlesPagingAdapter? = null
    lateinit var collectArticleViewModel: GetCollectArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_article)
        ImmerBarUtils.initImmerBar(this, R.color.alpha_gray_white)

        initToolbar()
        rv_collect_articles.layoutManager = LinearLayoutManager(this)
        collectArticleAdapter  = CollectArticlesPagingAdapter()
        rv_collect_articles.adapter = collectArticleAdapter

        collectArticleViewModel =
            ViewModelProviders.of(this).get(GetCollectArticleViewModel::class.java)

        lifecycle.addObserver(collectArticleViewModel)

        collectArticleViewModel.errorMsgLiveData.observe(this, errorMsgObserver)
        collectArticleViewModel.getArticlesLiveData().observe(this,
            Observer<PagedList<CollectArticleItemData>> { datasBeans ->

                collectArticleAdapter?.submitList(datasBeans)
            })


        collectArticleAdapter?.onRvItemClickListener = object : OnRvItemClickListener {
            override fun onItemClick(position: Int) {

                ActivityUtils.skipToArticle(
                    this@CollectArticleActivity,
                    collectArticleAdapter?.getArticleItemBean(position)?.id ?: 0,
                    collectArticleAdapter?.getArticleItemBean(position)?.title ?: "",
                    collectArticleAdapter?.getArticleItemBean(position)?.link ?: ""
                )
            }

        }
    }


    private fun initToolbar() {

        index_toolbar.setNavigationIcon(R.drawable.icon_back_gray)
        index_toolbar.setTitleTextColor(Color.WHITE)
        index_toolbar.setTitle("")
        index_toolbar.setNavigationOnClickListener {
            finish()
        }

        tv_title.text = StringUtils.getString(R.string.string_collect)
    }
}
