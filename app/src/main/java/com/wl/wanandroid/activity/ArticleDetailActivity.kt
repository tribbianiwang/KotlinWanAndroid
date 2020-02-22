package com.wl.wanandroid.activity

import android.graphics.Color
import android.os.Bundle
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.AppConstants.ARTICLE_ID
import com.wl.wanandroid.utils.AppConstants.ARTICLE_TITLE
import com.wl.wanandroid.utils.AppConstants.ARTICLE_URL
import com.wl.wanandroid.utils.T
import kotlinx.android.synthetic.main.activity_article_detail.*
import android.webkit.WebView
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import cn.sharesdk.onekeyshare.OnekeyShare











class ArticleDetailActivity : BaseActivity() {
    var articleTitle:String=""
    var articleId:Int = 0
    var articleUrl:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        articleTitle = intent.getStringExtra(ARTICLE_TITLE)
        articleUrl =  intent.getStringExtra(ARTICLE_URL)
        articleId =  intent.getIntExtra(ARTICLE_ID,0)
        tv_title.text = articleTitle

        initToolBar()
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
                T.showShort(this,"收藏")
            }else if(it.itemId== com.wl.wanandroid.R.id.bt_menu_share){
                showShare()
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
        wv_article.loadUrl(articleUrl)



        wv_article.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, url)
            }
        })


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
