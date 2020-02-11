package com.wl.wanandroid.viewmodel

import androidx.annotation.NonNull
import com.wl.wanandroid.bean.HomeArticleBean
import com.wl.wanandroid.model.HomeArticleModel
import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import com.wl.wanandroid.bean.HomeArticleData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.DataSource.Factory
import com.wl.wanandroid.utils.LogUtils


class HomeArticleViewModel:BaseViewModel<HomeArticleBean>() {
    var homeArticleModel = HomeArticleModel(this)
    private val TAG = HomeArticleViewModel::class.java!!.getSimpleName()
    private var articleRes: LiveData<PagedList<HomeArticleData>>? = null
    private var mDataSource: ArticleDataSource? = null
    //是否有数据
    private val boundaryPageData = MutableLiveData<Boolean>()


    init {
        baseModel = homeArticleModel
    }

    fun getHomeArticles(page: String,initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, HomeArticleData>?,loadMoreCallback:PageKeyedDataSource.LoadCallback<Int, HomeArticleData>?){
        homeArticleModel?.getHomeArticles(page,initDataCallBack,loadMoreCallback,boundaryPageData)

    }


    fun getArticleLiveData(): LiveData<PagedList<HomeArticleData>> {
        if (articleRes == null) {
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(22)
                .build()
            articleRes = LivePagedListBuilder<Int, HomeArticleData>(mFactory, config).setBoundaryCallback(mBoundaryCallback).build()
        }
        return articleRes as LiveData<PagedList<HomeArticleData>>
    }


    private val mFactory = object : Factory<Int, HomeArticleData>() {
        @NonNull
        override fun create(): DataSource<Int, HomeArticleData> {
            if (mDataSource == null) {
                mDataSource = ArticleDataSource()
            }
            return mDataSource as ArticleDataSource
        }
    }





    //监听数据边界
    private val mBoundaryCallback =
        object : PagedList.BoundaryCallback<HomeArticleData>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                //初始化数据
                boundaryPageData.postValue(false)
            }

            override fun onItemAtFrontLoaded(itemAtFront: HomeArticleData) {
                super.onItemAtFrontLoaded(itemAtFront)
                //正在添加数据
                boundaryPageData.postValue(true)
            }

            override fun onItemAtEndLoaded(itemAtEnd: HomeArticleData) {
                super.onItemAtEndLoaded(itemAtEnd)
                //没有数据加载了
                boundaryPageData.postValue(false)
            }
        }

    fun getDataSource(): ArticleDataSource? {
        return mDataSource
    }

    fun getBoundaryPageData(): MutableLiveData<Boolean> {
        return boundaryPageData
    }

    inner class ArticleDataSource : PageKeyedDataSource<Int, HomeArticleData>() {
        override fun loadInitial(
            params: PageKeyedDataSource.LoadInitialParams<Int>,
            callback: PageKeyedDataSource.LoadInitialCallback<Int, HomeArticleData>
        ) {
            //开始加载数据
            getHomeArticles(0.toString(),callback,null)
//            loadData(0, callback, null)
            LogUtils.d(TAG, "loadInitial")
        }

        override fun loadBefore(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, HomeArticleData>
        ) {
            //往前加载数据
        }

        override fun loadAfter(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, HomeArticleData>
        ) {
            //往后加载数据
            getHomeArticles((params.key + 1).toString(), null, callback)
//            loadData(params.key + 1, null, callback)
            LogUtils.d(TAG, "loadAfter")
        }
    }


}