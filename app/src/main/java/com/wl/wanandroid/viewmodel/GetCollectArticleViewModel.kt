package com.wl.wanandroid.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.wl.wanandroid.bean.CollectArticleBean
import com.wl.wanandroid.bean.CollectArticleItemData
import com.wl.wanandroid.model.GetCollectArticlesModel
import com.wl.wanandroid.model.GetSystemChildArticlesModel

class GetCollectArticleViewModel:BaseViewModel<CollectArticleBean>() {
   
    var articleRes: LiveData<PagedList<CollectArticleItemData>>? = null
    var mDataSource: SystemChildArtricleDataSource ?= null
    private val boundaryPageData = MutableLiveData<Boolean>()
    var getCollectChildArticleModel = GetCollectArticlesModel(this)

    init {
        baseModel = getCollectChildArticleModel
    }

  

    fun getCollectArticles(page: String, initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, CollectArticleItemData>?, loadMoreCallback:PageKeyedDataSource.LoadCallback<Int, CollectArticleItemData>?){
        getCollectChildArticleModel?.getCollectArticles(page,initDataCallBack,loadMoreCallback,boundaryPageData)


    }
    

    fun getArticlesLiveData(): LiveData<PagedList<CollectArticleItemData>> {
        if (articleRes == null) {
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build()
            articleRes = LivePagedListBuilder<Int, CollectArticleItemData>(mFactory, config).setBoundaryCallback(mBoundaryCallback).build()
        }
        return articleRes as LiveData<PagedList<CollectArticleItemData>>
    }


    private val mFactory = object : DataSource.Factory<Int, CollectArticleItemData>() {
        @NonNull
        override fun create(): DataSource<Int, CollectArticleItemData> {

//            if (mDataSource == null) {
            mDataSource = SystemChildArtricleDataSource()
//            }
            return mDataSource as SystemChildArtricleDataSource
        }
    }





    //监听数据边界
    private val mBoundaryCallback =
        object : PagedList.BoundaryCallback<CollectArticleItemData>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                //初始化数据
                boundaryPageData.postValue(false)
            }

            override fun onItemAtFrontLoaded(itemAtFront: CollectArticleItemData) {
                super.onItemAtFrontLoaded(itemAtFront)
                //正在添加数据
                boundaryPageData.postValue(true)
            }

            override fun onItemAtEndLoaded(itemAtEnd: CollectArticleItemData) {
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

    inner class SystemChildArtricleDataSource : PageKeyedDataSource<Int, CollectArticleItemData>() {
        override fun loadInitial(
            params: PageKeyedDataSource.LoadInitialParams<Int>,
            callback: PageKeyedDataSource.LoadInitialCallback<Int, CollectArticleItemData>
        ) {

            //开始加载数据
            getCollectArticles(0.toString(),callback,null)
//            loadData(0, callback, null)
        }

        override fun loadBefore(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, CollectArticleItemData>
        ) {
            //往前加载数据
        }

        override fun loadAfter(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, CollectArticleItemData>
        ) {
            //往后加载数据
            getCollectArticles((params.key + 1).toString(), null, callback)
//            loadData(params.key + 1, null, callback)

        }
    }
}