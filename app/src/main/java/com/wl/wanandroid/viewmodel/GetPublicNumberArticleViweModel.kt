package com.wl.wanandroid.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.wl.wanandroid.bean.PublicNumberArticleBean
import com.wl.wanandroid.bean.PublicNumberArticleData
import com.wl.wanandroid.model.GetPublicNumberArticleModel

class GetPublicNumberArticleViweModel:BaseViewModel<PublicNumberArticleBean>() {

    var getPublicNumberArticleModel = GetPublicNumberArticleModel(this)

    private var articleRes: LiveData<PagedList<PublicNumberArticleData>>? = null
    private var mDataSource: ArticleDataSource? = null
    //是否有数据
    private val boundaryPageData = MutableLiveData<Boolean>()

    var publicNumberId:String = ""
    
    
    init {
        baseModel = getPublicNumberArticleModel
    }




    fun getPublicArticles(page: String, initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, PublicNumberArticleData>?, loadMoreCallback:PageKeyedDataSource.LoadCallback<Int, PublicNumberArticleData>?){
        getPublicNumberArticleModel?.getPublicNumber(publicNumberId,page,initDataCallBack,loadMoreCallback,boundaryPageData)

    }


    fun getArticleLiveData(): LiveData<PagedList<PublicNumberArticleData>> {
        if (articleRes == null) {
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(22)
                .build()
            articleRes = LivePagedListBuilder<Int, PublicNumberArticleData>(mFactory, config).setBoundaryCallback(mBoundaryCallback).build()
        }
        return articleRes as LiveData<PagedList<PublicNumberArticleData>>
    }


    private val mFactory = object : DataSource.Factory<Int, PublicNumberArticleData>() {
        @NonNull
        override fun create(): DataSource<Int, PublicNumberArticleData> {
            if (mDataSource == null) {
                mDataSource = ArticleDataSource()
            }
            return mDataSource as ArticleDataSource
        }
    }





    //监听数据边界
    private val mBoundaryCallback =
        object : PagedList.BoundaryCallback<PublicNumberArticleData>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                //初始化数据
                boundaryPageData.postValue(false)
            }

            override fun onItemAtFrontLoaded(itemAtFront: PublicNumberArticleData) {
                super.onItemAtFrontLoaded(itemAtFront)
                //正在添加数据
                boundaryPageData.postValue(true)
            }

            override fun onItemAtEndLoaded(itemAtEnd: PublicNumberArticleData) {
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

    inner class ArticleDataSource : PageKeyedDataSource<Int, PublicNumberArticleData>() {
        override fun loadInitial(
            params: PageKeyedDataSource.LoadInitialParams<Int>,
            callback: PageKeyedDataSource.LoadInitialCallback<Int, PublicNumberArticleData>
        ) {
            //开始加载数据
            getPublicArticles(0.toString(),callback,null)
//            loadData(0, callback, null)

        }

        override fun loadBefore(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, PublicNumberArticleData>
        ) {
            //往前加载数据
        }

        override fun loadAfter(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, PublicNumberArticleData>
        ) {
            //往后加载数据
            getPublicArticles((params.key + 1).toString(), null, callback)
//            loadData(params.key + 1, null, callback)

        }
    }

}