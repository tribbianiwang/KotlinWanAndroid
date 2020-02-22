package com.wl.wanandroid.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.RvSystemChildAdapter
import com.wl.wanandroid.adapter.SystemChildArticlesPagingAdapter
import com.wl.wanandroid.bean.SearchResultItemData
import com.wl.wanandroid.bean.SystemChildArticleBean
import com.wl.wanandroid.bean.SystemChildArticleItemData
import com.wl.wanandroid.model.GetSystemChildArticlesModel
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.GetSystemChildArticleViewModel
import kotlinx.android.synthetic.main.activity_public_number.*
import kotlinx.android.synthetic.main.activity_public_number.index_toolbar
import kotlinx.android.synthetic.main.activity_public_number.tv_title
import kotlinx.android.synthetic.main.activity_system_child.*

class SystemChildActivity : BaseActivity() {

    var systemChildName:String = ""
    var systemChildId:Int = 0
    var rvSystemChildArticlesAdapter:SystemChildArticlesPagingAdapter?=null

    lateinit var  getSystemChildArticlesViewModel:GetSystemChildArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_child)


        systemChildName = intent.getStringExtra(AppConstants.SYSTEM_NAME)
        systemChildId = intent.getIntExtra(AppConstants.SYSTEM_ID,0)

        initToolbar()
        rv_system_child_articles.layoutManager = LinearLayoutManager(this)
        rvSystemChildArticlesAdapter = SystemChildArticlesPagingAdapter()
        rv_system_child_articles.adapter = rvSystemChildArticlesAdapter

        getSystemChildArticlesViewModel = ViewModelProviders.of(this).get(GetSystemChildArticleViewModel::class.java)

        lifecycle.addObserver(getSystemChildArticlesViewModel)

        var articlesObserver = Observer<SystemChildArticleBean>{
            LogUtils.d("systemchild","size:${it?.data?.size}")
        }

        getSystemChildArticlesViewModel.baseResultLiveData.observe(this,articlesObserver)
        getSystemChildArticlesViewModel.errorMsgLiveData.observe(this,errorMsgObserver )

        getSystemChildArticlesViewModel.cid =systemChildId.toString()

        getSystemChildArticlesViewModel.getArticlesLiveData().observe(this,
            Observer<PagedList<SystemChildArticleItemData>> { datasBeans ->
                rvSystemChildArticlesAdapter?.submitList(datasBeans) })

    }

    private fun initToolbar() {

        index_toolbar.setNavigationIcon(R.drawable.icon_back_gray)
        index_toolbar.setTitleTextColor(Color.WHITE)
        index_toolbar.setTitle("")
        index_toolbar.setNavigationOnClickListener {
            finish()
        }

        tv_title.text = systemChildName
    }
}