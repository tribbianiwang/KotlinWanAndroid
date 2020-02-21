package com.wl.wanandroid.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.RvHotSearchAdapter
import com.wl.wanandroid.adapter.RvSearchHistoryAdapter
import com.wl.wanandroid.adapter.SearchResultPagingAdapter
import com.wl.wanandroid.bean.HotSearchBean
import com.wl.wanandroid.bean.SearchResultBean
import com.wl.wanandroid.bean.SearchResultItemData
import com.wl.wanandroid.dao.AppDataBase
import com.wl.wanandroid.dao.SearchHistoryDao
import com.wl.wanandroid.dao.SearchHistoryKeyDaoBean
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.viewmodel.GetHotSearchViewModel
import com.wl.wanandroid.viewmodel.StartSearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

import android.content.Context
import android.view.inputmethod.InputMethodManager


class SearchActivity : BaseActivity() {
    lateinit var getHotSearchViewModel:GetHotSearchViewModel
    lateinit var startSearchViewModel:StartSearchViewModel
    lateinit var   searchHistoryDao:SearchHistoryDao
    var searchHistoryKeyBeans :List<SearchHistoryKeyDaoBean> = ArrayList()


    var rvHotSearchAdapter:RvHotSearchAdapter?=null
    var rvSearchHistoryAdapter:RvSearchHistoryAdapter?=null
    lateinit var rvSearchResultAdapter:SearchResultPagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rv_search_hot.layoutManager = ChipsLayoutManager.newBuilder(this)
            .setOrientation(ChipsLayoutManager.HORIZONTAL).setRowStrategy(ChipsLayoutManager.STRATEGY_FILL_SPACE)
            .build();
        rv_search_history.layoutManager = ChipsLayoutManager.newBuilder(this)
            .setOrientation(ChipsLayoutManager.HORIZONTAL).setRowStrategy(ChipsLayoutManager.STRATEGY_FILL_SPACE)
            .build();

        searchHistoryDao = AppDataBase.getDatabase(this).searchHistoryDao()

        searchHistoryKeyBeans = searchHistoryDao.getAllSearchHistory()
        if(searchHistoryKeyBeans.size==0){
            rl_search_history.visibility = View.GONE
            rv_search_history.visibility =View.GONE
        }else{
            rl_search_history.visibility = View.VISIBLE
            rv_search_history.visibility =View.VISIBLE

            rvSearchHistoryAdapter = RvSearchHistoryAdapter(searchHistoryKeyBeans)
            rv_search_history.adapter  = rvSearchHistoryAdapter
            rvSearchHistoryAdapter?.onItemClickListener = object :OnRvItemClickListener{
                override fun onItemClick(position: Int) {

                    searchHistoryKeyBeans.get(position)?.searchKey?.let { startSearch(it) }
                }

            }

            iv_clear_search_history.setOnClickListener {
                searchHistoryDao.deleteAllSearchHistory()
                rl_search_history.visibility = View.GONE
                rv_search_history.visibility =View.GONE
            }

            LogUtils.d("searchHistorySize:","size:${searchHistoryKeyBeans.size}")
        }


        setGloadView(rv_search_result)




        rv_search_result.layoutManager = LinearLayoutManager(this)
        rvSearchResultAdapter = SearchResultPagingAdapter()
        rv_search_result.adapter = rvSearchResultAdapter

        getHotSearchViewModel = ViewModelProviders.of(this).get(GetHotSearchViewModel::class.java)
        startSearchViewModel = ViewModelProviders.of(this).get(StartSearchViewModel::class.java)


        lifecycle.addObserver(getHotSearchViewModel)
        lifecycle.addObserver(startSearchViewModel)

        var hotSearchObserver:Observer<HotSearchBean> = object :Observer<HotSearchBean>{
            override fun onChanged(t: HotSearchBean?) {
                LogUtils.d("hotSearch","hotsearchSize${t?.data?.size}")
                rvHotSearchAdapter = t?.data?.let { RvHotSearchAdapter(it) }
                rv_search_hot.adapter  = rvHotSearchAdapter
                rvHotSearchAdapter?.onItemClickListener = object :OnRvItemClickListener{
                    override fun onItemClick(position: Int) {

                        t?.data?.get(position)?.name?.let { startSearch(it) }
                    }

                }
            }

        }

        var searchResultBeanObserver:Observer<SearchResultBean> = Observer {
            LogUtils.d("startSearchObserver:","size:${it.data.size}")
        }

        getHotSearchViewModel.baseResultLiveData.observe(this,hotSearchObserver)
        getHotSearchViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        getHotSearchViewModel.errorMsgLiveData.observe(this,errorMsgObserver)

        startSearchViewModel.baseResultLiveData.observe(this,searchResultBeanObserver)
        startSearchViewModel.queryStatusLiveData.observe(this,gLoadingqueryStatusObserver)
        startSearchViewModel.errorMsgLiveData.observe(this,errorMsgObserver)

        getHotSearchViewModel.getHotSearch()

        tv_search.setOnClickListener {
            if(TextUtils.isEmpty(et_search.text.toString())){
                et_search.setError(StringUtils.getString(R.string.string_notinput_warning))
            }else{
                startSearch(et_search.text.toString())
            }


        }

        iv_close.setOnClickListener {
            finish()
        }

        et_search.setOnEditorActionListener { v, actionId, event ->

            if(actionId==EditorInfo.IME_ACTION_SEARCH){
                (et_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(

                            this.getCurrentFocus()?.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS
                    )

                if(TextUtils.isEmpty(et_search.text.toString())){
                    et_search.setError(StringUtils.getString(R.string.string_notinput_warning))
                }else{
                    startSearch(et_search.text.toString())
                }
                 true
            }else{
                 false
            }


        }


    }

    fun startSearch(key:String){
        et_search.setText(key)

        ll_hotsearch_history.visibility = View.GONE
        rv_search_result.visibility = View.VISIBLE
        startSearchViewModel.searchKey = key

        startSearchViewModel.getDataSource()?.invalidate()

        startSearchViewModel.getSearchResultLiveData().observe(this,
            Observer<PagedList<SearchResultItemData>> { datasBeans ->
                LogUtils.d("searchResultSize","${datasBeans.size}")
                rvSearchResultAdapter.submitList(datasBeans) })

        searchHistoryDao.insertSearchHistory(SearchHistoryKeyDaoBean(key))




    }


    override fun onLoadRetry() {
        super.onLoadRetry()
        startSearch(et_search.text.toString())
    }



}
