package com.wl.wanandroid.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.RvHotSearchAdapter
import com.wl.wanandroid.bean.HotSearchBean
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.GetHotSearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {
    lateinit var getHotSearchViewModel:GetHotSearchViewModel
    var rvHotSearchAdapter:RvHotSearchAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rv_search_hot.layoutManager = ChipsLayoutManager.newBuilder(this)
            .setOrientation(ChipsLayoutManager.HORIZONTAL).setRowStrategy(ChipsLayoutManager.STRATEGY_FILL_SPACE)
            .build();

        getHotSearchViewModel = ViewModelProviders.of(this).get(GetHotSearchViewModel::class.java)

        lifecycle.addObserver(getHotSearchViewModel)

        var hotSearchObserver:Observer<HotSearchBean> = object :Observer<HotSearchBean>{
            override fun onChanged(t: HotSearchBean?) {
                LogUtils.d("hotSearch","hotsearchSize${t?.data?.size}")
                rvHotSearchAdapter = t?.data?.let { RvHotSearchAdapter(it) }
                rv_search_hot.adapter  = rvHotSearchAdapter
            }

        }

        getHotSearchViewModel.baseResultLiveData.observe(this,hotSearchObserver)
        getHotSearchViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        getHotSearchViewModel.errorMsgLiveData.observe(this,errorMsgObserver)

        getHotSearchViewModel.getHotSearch()
    }
}
