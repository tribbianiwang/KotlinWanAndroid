package com.wl.wanandroid.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.SystemChildArticleBean
import com.wl.wanandroid.model.GetSystemChildArticlesModel
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.GetSystemChildArticleViewModel
import kotlinx.android.synthetic.main.activity_public_number.*

class SystemChildActivity : BaseActivity() {

    var systemChildName:String = ""
    var systemChildId:Int = 0

    lateinit var  getSystemChildArticlesViewModel:GetSystemChildArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_child)


        systemChildName = intent.getStringExtra(AppConstants.SYSTEM_NAME)
        systemChildId = intent.getIntExtra(AppConstants.SYSTEM_ID,0)

        initToolbar()

        getSystemChildArticlesViewModel = ViewModelProviders.of(this).get(GetSystemChildArticleViewModel::class.java)

        lifecycle.addObserver(getSystemChildArticlesViewModel)

        var articlesObserver = Observer<SystemChildArticleBean>{
            LogUtils.d("systemchild","size:${it?.data?.size}")
        }

        getSystemChildArticlesViewModel.baseResultLiveData.observe(this,articlesObserver)
        getSystemChildArticlesViewModel.errorMsgLiveData.observe(this,errorMsgObserver )

        getSystemChildArticlesViewModel.getSystemChildArticles(0.toString(),systemChildId.toString())


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
