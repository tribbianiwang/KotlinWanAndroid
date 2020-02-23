package com.wl.wanandroid.activity

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import cn.sharesdk.onekeyshare.OnekeyShare
import com.wl.wanandroid.R
import com.wl.wanandroid.bean.CollectArticleResultBean
import com.wl.wanandroid.utils.AppConstants.ARTICLE_ID
import com.wl.wanandroid.utils.AppConstants.ARTICLE_TITLE
import com.wl.wanandroid.utils.AppConstants.ARTICLE_URL
import com.wl.wanandroid.utils.ImmerBarUtils
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.utils.StringUtils.setClipboard
import com.wl.wanandroid.utils.T
import com.wl.wanandroid.viewmodel.StartCollectArticleViewModel
import kotlinx.android.synthetic.main.activity_article_detail.*


class ArticleDetailActivity : BaseActivity() {
    var articleTitle:String=""
    var articleId:Int = 0
    var articleUrl:String = ""
    lateinit var startCollectArticleViewModel: StartCollectArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        ImmerBarUtils.initImmerBar(this,R.color.alpha_gray_white)

        articleTitle = intent.getStringExtra(ARTICLE_TITLE)
        articleUrl =  intent.getStringExtra(ARTICLE_URL)
        articleId =  intent.getIntExtra(ARTICLE_ID,0)
        tv_title.text = articleTitle

        initToolBar()

        startCollectArticleViewModel = ViewModelProviders.of(this).get(StartCollectArticleViewModel::class.java)

        lifecycle.addObserver(startCollectArticleViewModel)

        var collectArticleObserver = Observer<CollectArticleResultBean>{
            T.showShort(ArticleDetailActivity@this,"收藏成功")
        }

        startCollectArticleViewModel.baseResultLiveData.observe(this,collectArticleObserver)
        startCollectArticleViewModel.queryStatusLiveData.observe(this,queryStatusObserver)
        startCollectArticleViewModel.errorMsgLiveData.observe(this, errorMsgObserver)
    }

    /**
     * 初始化toolbar
     */
    private fun initToolBar() {

        index_toolbar.setNavigationIcon(R.drawable.icon_back_gray)
        index_toolbar.inflateMenu(R.menu.menu_article_detail)
        //        setSupportActionBar(index_toolbar);
        index_toolbar.setTitleTextColor(Color.WHITE)
        index_toolbar.setTitle("")
        index_toolbar.setNavigationOnClickListener {
            finish()

        }
        index_toolbar.setOnMenuItemClickListener {
            if(it.itemId==R.id.bt_menu_collect){
                LogUtils.d("articleDetailActivity","收藏${articleId}")
//                T.showShort(this,"收藏"+articleId)
                startCollectArticleViewModel.startCollectArticle(articleId.toString())
            }else if(it.itemId== com.wl.wanandroid.R.id.bt_menu_share){
                setClipboard(ArticleDetailActivity@this,articleUrl)
                T.showShort(ArticleDetailActivity@this,StringUtils.getString(R.string.string_shared_url_hint))
//                showShare()
            }

             false
        }

        initWebView()
    }

    //java
    private fun showShare() {
        val oks = OnekeyShare()
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(articleTitle)
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(articleUrl)
        // text是分享文本，所有平台都需要这个字段
        oks.text = articleTitle
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg")
        // url在微信、Facebook等平台中使用
        oks.setUrl(articleUrl)
        // 启动分享GUI
        oks.show(this)
    }


    private fun initWebView() {

        wv_article.getSettings().setJavaScriptEnabled(true)
        wv_article.getSettings().setSupportZoom(true)
        wv_article.getSettings().setBuiltInZoomControls(true)

        wv_article.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                wv_article.loadUrl(url)
                return true
            }
        })


        wv_article.loadUrl(articleUrl)





        wv_article.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress >= 100) {
                    hideProgress()
                } else {
                    showProgress(view.context)
                }

            }
        })

    }
}
