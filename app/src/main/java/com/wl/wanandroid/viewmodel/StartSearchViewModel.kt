package com.wl.wanandroid.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.wl.wanandroid.bean.SearchResultBean
import com.wl.wanandroid.bean.SearchResultItemData
import com.wl.wanandroid.model.StartSearchModel

class StartSearchViewModel:BaseViewModel<SearchResultBean>() {
    var startSearchModel = StartSearchModel(this)

    private var articleRes: LiveData<PagedList<SearchResultItemData>>? = null
    private var mDataSource: ArticleDataSource? = null
    var searchKey:String=""

    //是否有数据
    private val boundaryPageData = MutableLiveData<Boolean>()
    init {
        baseModel = startSearchModel
    }



    fun startSearch(page: String, initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, SearchResultItemData>?, loadMoreCallback:PageKeyedDataSource.LoadCallback<Int, SearchResultItemData>?){
        startSearchModel?.startSearch(page,searchKey,initDataCallBack,loadMoreCallback,boundaryPageData)

    }


    fun getSearchResultLiveData(): LiveData<PagedList<SearchResultItemData>> {
        if (articleRes == null) {
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(22)
                .build()
            articleRes = LivePagedListBuilder<Int, SearchResultItemData>(mFactory, config).setBoundaryCallback(mBoundaryCallback).build()
        }
        return articleRes as LiveData<PagedList<SearchResultItemData>>
    }


    private val mFactory = object : DataSource.Factory<Int, SearchResultItemData>() {
        @NonNull
        override fun create(): DataSource<Int, SearchResultItemData> {
            if (mDataSource == null) {
                mDataSource = ArticleDataSource()
            }
            return mDataSource as ArticleDataSource
        }
    }





    //监听数据边界
    private val mBoundaryCallback =
        object : PagedList.BoundaryCallback<SearchResultItemData>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                //初始化数据
                boundaryPageData.postValue(false)
            }

            override fun onItemAtFrontLoaded(itemAtFront: SearchResultItemData) {
                super.onItemAtFrontLoaded(itemAtFront)
                //正在添加数据
                boundaryPageData.postValue(true)
            }

            override fun onItemAtEndLoaded(itemAtEnd: SearchResultItemData) {
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

    inner class ArticleDataSource : PageKeyedDataSource<Int, SearchResultItemData>() {
        override fun loadInitial(
            params: PageKeyedDataSource.LoadInitialParams<Int>,
            callback: PageKeyedDataSource.LoadInitialCallback<Int, SearchResultItemData>
        ) {
            //开始加载数据
            startSearch(0.toString(),callback,null)
//            loadData(0, callback, null)
        }

        override fun loadBefore(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, SearchResultItemData>
        ) {
            //往前加载数据
        }

        override fun loadAfter(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, SearchResultItemData>
        ) {
            //往后加载数据
            startSearch((params.key + 1).toString(), null, callback)
//            loadData(params.key + 1, null, callback)

        }
    }

}