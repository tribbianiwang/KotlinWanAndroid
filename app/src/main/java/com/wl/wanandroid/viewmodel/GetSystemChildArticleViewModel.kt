package com.wl.wanandroid.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.wl.wanandroid.bean.SystemChildArticleItemData
import com.wl.wanandroid.bean.SystemChildArticleBean
import com.wl.wanandroid.model.GetSystemChildArticlesModel

class GetSystemChildArticleViewModel:BaseViewModel<SystemChildArticleBean>() {
    var cid:String=""
    var articleRes: LiveData<PagedList<SystemChildArticleItemData>>? = null
    var mDataSource: SystemChildArtricleDataSource ?= null
    private val boundaryPageData = MutableLiveData<Boolean>()
    var getSystemChildArticleModel = GetSystemChildArticlesModel(this)

    init {
        baseModel = getSystemChildArticleModel
    }

  

    fun getSystemChildArticles(page: String, initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, SystemChildArticleItemData>?, loadMoreCallback:PageKeyedDataSource.LoadCallback<Int, SystemChildArticleItemData>?){
        getSystemChildArticleModel?.getSystemChildArticles(page,cid,initDataCallBack,loadMoreCallback,boundaryPageData)


    }
    

    fun getArticlesLiveData(): LiveData<PagedList<SystemChildArticleItemData>> {
        if (articleRes == null) {
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build()
            articleRes = LivePagedListBuilder<Int, SystemChildArticleItemData>(mFactory, config).setBoundaryCallback(mBoundaryCallback).build()
        }
        return articleRes as LiveData<PagedList<SystemChildArticleItemData>>
    }


    private val mFactory = object : DataSource.Factory<Int, SystemChildArticleItemData>() {
        @NonNull
        override fun create(): DataSource<Int, SystemChildArticleItemData> {

//            if (mDataSource == null) {
            mDataSource = SystemChildArtricleDataSource()
//            }
            return mDataSource as SystemChildArtricleDataSource
        }
    }





    //监听数据边界
    private val mBoundaryCallback =
        object : PagedList.BoundaryCallback<SystemChildArticleItemData>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                //初始化数据
                boundaryPageData.postValue(false)
            }

            override fun onItemAtFrontLoaded(itemAtFront: SystemChildArticleItemData) {
                super.onItemAtFrontLoaded(itemAtFront)
                //正在添加数据
                boundaryPageData.postValue(true)
            }

            override fun onItemAtEndLoaded(itemAtEnd: SystemChildArticleItemData) {
                super.onItemAtEndLoaded(itemAtEnd)
                //没有数据加载了
                boundaryPageData.postValue(false)
            }
        }

    fun getDataSource(): SystemChildArtricleDataSource? {

        return mDataSource
    }

    fun getBoundaryPageData(): MutableLiveData<Boolean> {
        return boundaryPageData
    }

    inner class SystemChildArtricleDataSource : PageKeyedDataSource<Int, SystemChildArticleItemData>() {
        override fun loadInitial(
            params: PageKeyedDataSource.LoadInitialParams<Int>,
            callback: PageKeyedDataSource.LoadInitialCallback<Int, SystemChildArticleItemData>
        ) {

            //开始加载数据
            getSystemChildArticles(0.toString(),callback,null)
//            loadData(0, callback, null)
        }

        override fun loadBefore(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, SystemChildArticleItemData>
        ) {
            //往前加载数据
        }

        override fun loadAfter(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, SystemChildArticleItemData>
        ) {
            //往后加载数据
            getSystemChildArticles((params.key + 1).toString(), null, callback)
//            loadData(params.key + 1, null, callback)

        }
    }
}