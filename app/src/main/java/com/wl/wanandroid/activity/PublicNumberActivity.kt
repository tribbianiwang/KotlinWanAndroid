package com.wl.wanandroid.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.PublicNumberArticleBean
import com.wl.wanandroid.model.GetPublicNumberArticleModel
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.viewmodel.GetPublicNumberArticleViweModel

import kotlinx.android.synthetic.main.activity_public_number.*


class PublicNumberActivity : BaseActivity() {

    var publicNumberName:String = ""
    var publicNumberId:Int = 0
    lateinit var getPublicNumberArticleViewModel :GetPublicNumberArticleViweModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_number)
        publicNumberName = intent.getStringExtra(AppConstants.PUBLIC_NUMBER_NAME)
        publicNumberId = intent.getIntExtra(AppConstants.PUBLIC_NUMBER_ID,0)

        initToolbar()
        rv_publicnumber_articles.layoutManager = LinearLayoutManager(this)


        getPublicNumberArticleViewModel = ViewModelProviders.of(this).get(GetPublicNumberArticleViweModel::class.java)

        lifecycle.addObserver(getPublicNumberArticleViewModel)

        var publicNumberArticleObserver = Observer<PublicNumberArticleBean>{
            LogUtils.d("publicnumberar","size:${it.data.datas.size}")
        }

        setGloadView(rv_publicnumber_articles)

        getPublicNumberArticleViewModel.baseResultLiveData.observe(this,publicNumberArticleObserver)
        getPublicNumberArticleViewModel.queryStatusLiveData.observe(this,gLoadingqueryStatusObserver)
        getPublicNumberArticleViewModel.errorMsgLiveData.observe(this,errorMsgObserver)

        getPublicNumberArticleViewModel.getPublicNumber( publicNumberId.toString(),0.toString())
    }

    private fun initToolbar() {

        index_toolbar.setNavigationIcon(R.drawable.icon_back_gray)
        index_toolbar.setTitleTextColor(Color.WHITE)
        index_toolbar.setTitle("")
        index_toolbar.setNavigationOnClickListener {
            finish()
        }

        tv_title.text = publicNumberName
    }


}
