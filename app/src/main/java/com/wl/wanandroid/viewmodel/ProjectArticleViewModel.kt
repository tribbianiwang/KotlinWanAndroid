package com.wl.wanandroid.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.wl.wanandroid.bean.ProjectArticleBean
import com.wl.wanandroid.bean.ProjectArticleData
import com.wl.wanandroid.model.ProjectArticleModel
import com.wl.wanandroid.utils.LogUtils

class ProjectArticleViewModel:BaseViewModel<ProjectArticleBean>() {

    var projectTreeId:Int=-1
    var projectArticleModel = ProjectArticleModel(this)
    private var articleRes: LiveData<PagedList<ProjectArticleData>>? = null
    private var mDataSource: ArticleDataSource? = null
    //是否有数据
    private val boundaryPageData = MutableLiveData<Boolean>()
    init {
        baseModel = projectArticleModel

    }
    fun getProjectArticles(page:Int,initDataCallBack: PageKeyedDataSource.LoadInitialCallback<Int, ProjectArticleData>?,loadMoreCallback:PageKeyedDataSource.LoadCallback<Int, ProjectArticleData>?){
        projectArticleModel.getProjectArticles(page,projectTreeId,initDataCallBack,loadMoreCallback,boundaryPageData)
    }


    fun getArticleLiveData(): LiveData<PagedList<ProjectArticleData>> {
        if (articleRes == null) {
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(22)
                .build()
            articleRes = LivePagedListBuilder<Int, ProjectArticleData>(mFactory, config).setBoundaryCallback(mBoundaryCallback).build()
        }
        return articleRes as LiveData<PagedList<ProjectArticleData>>
    }

    private val mFactory = object : DataSource.Factory<Int, ProjectArticleData>() {
        @NonNull
        override fun create(): DataSource<Int, ProjectArticleData> {
            if (mDataSource == null) {
                mDataSource = ArticleDataSource()
            }
            return mDataSource as ArticleDataSource
        }
    }


    //监听数据边界
    private val mBoundaryCallback =
        object : PagedList.BoundaryCallback<ProjectArticleData>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                //初始化数据
                boundaryPageData.postValue(false)
            }

            override fun onItemAtFrontLoaded(itemAtFront: ProjectArticleData) {
                super.onItemAtFrontLoaded(itemAtFront)
                //正在添加数据
                boundaryPageData.postValue(true)
            }

            override fun onItemAtEndLoaded(itemAtEnd: ProjectArticleData) {
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

    inner class ArticleDataSource : PageKeyedDataSource<Int, ProjectArticleData>() {
        override fun loadInitial(
            params: PageKeyedDataSource.LoadInitialParams<Int>,
            callback: PageKeyedDataSource.LoadInitialCallback<Int, ProjectArticleData>
        ) {
            //开始加载数据
            getProjectArticles(0,callback,null)
//            loadData(0, callback, null)
        }

        override fun loadBefore(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, ProjectArticleData>
        ) {
            //往前加载数据
        }

        override fun loadAfter(
            params: PageKeyedDataSource.LoadParams<Int>,
            callback: PageKeyedDataSource.LoadCallback<Int, ProjectArticleData>
        ) {
            //往后加载数据
            getProjectArticles((params.key + 1), null, callback)
//            loadData(params.key + 1, null, callback)
        }
    }
}