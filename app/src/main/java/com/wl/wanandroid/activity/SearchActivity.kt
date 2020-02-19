package com.wl.wanandroid.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.tudaritest.util.OnRvItemClickListener
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.RvHotSearchAdapter
import com.wl.wanandroid.bean.HotSearchBean
import com.wl.wanandroid.bean.SearchResultBean
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.viewmodel.GetHotSearchViewModel
import com.wl.wanandroid.viewmodel.StartSearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {
    lateinit var getHotSearchViewModel:GetHotSearchViewModel
    lateinit var startSearchViewModel:StartSearchViewModel

    var rvHotSearchAdapter:RvHotSearchAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rv_search_hot.layoutManager = ChipsLayoutManager.newBuilder(this)
            .setOrientation(ChipsLayoutManager.HORIZONTAL).setRowStrategy(ChipsLayoutManager.STRATEGY_FILL_SPACE)
            .build();

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
        startSearchViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
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
    }

    fun startSearch(key:String){
        LogUtils.d("startSearch",key)
        startSearchViewModel.startSearch(0,key)
    }
}
