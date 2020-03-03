package com.wl.wanandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatViewInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tencent.mmkv.MMKV
import com.wl.wanandroid.R
import com.wl.wanandroid.activity.AboutActivity
import com.wl.wanandroid.activity.CollectArticleActivity
import com.wl.wanandroid.activity.LoginActivity
import com.wl.wanandroid.bean.LogoutBean
import com.wl.wanandroid.model.LogoutModel
import com.wl.wanandroid.utils.*
import com.wl.wanandroid.viewmodel.LogOutViewModel
import kotlinx.android.synthetic.main.activity_collect_article.*
import kotlinx.android.synthetic.main.layout_fragment_mine.*
import kotlin.random.Random

class MineFragment : BaseFragment() {

    lateinit var logOutViewmodel:LogOutViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var contentView =inflater.inflate(R.layout.layout_fragment_mine,container,false)

        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        logOutViewmodel = ViewModelProviders.of(this).get(LogOutViewModel::class.java)

        lifecycle.addObserver(logOutViewmodel)

        var logoutObserver = Observer<LogoutBean>{
            context?.let { it1 -> T.showShort(it1,StringUtils.getString(R.string.string_logout_success)) }

        }

        tv_please_login.setOnClickListener {
            startActivity(Intent(activity,LoginActivity::class.java))
        }

        rv_quit_login.setOnClickListener {
            MMKV.defaultMMKV().encode(AppConstants.SAVED_USERNAME,"")
            MMKV.defaultMMKV().encode(AppConstants.SAVED_USER_ID,"")
            context?.sendBroadcast(Intent(AppConstants.USERLOGOUTBROADCAST))
            logOutViewmodel.logOut()
            rv_quit_login.visibility = View.GONE

        }

        rl_collect.setOnClickListener {
            startActivity(Intent(context,CollectArticleActivity::class.java))
        }

        rv_about.setOnClickListener {
            startActivity(Intent(context,AboutActivity::class.java))
        }

        logOutViewmodel.baseResultLiveData.observe(this,logoutObserver)
        logOutViewmodel.queryStatusLiveData.observe(this,queryStatusObserver)
        logOutViewmodel.errorMsgLiveData.observe(this,errorMsgObserver)

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmerBarUtils.initImmerBar(this,R.color.white)
    }

    //标志当前页面是否可见
    private var isFragmentVisible: Boolean = false

    override fun setUserVisibleHint(isFragmentVisibleToUser: Boolean) {
        super.setUserVisibleHint(isFragmentVisibleToUser)
        LogUtils.d("isUserVisible","isUserVIsi${userVisibleHint}")
        //懒加载
        if (userVisibleHint) {
            isFragmentVisible = true
            setLoginStatus()

        } else {
            isFragmentVisible = false

        }

    }

    override fun loginSuccess() {
        super.loginSuccess()
        setLoginStatus()

    }

    fun setLoginStatus(){
        if(!TextUtils.isEmpty(MMKV.defaultMMKV().decodeString(AppConstants.SAVED_USER_ID))){
            tv_user_name.setText(MMKV.defaultMMKV().decodeString(AppConstants.SAVED_USERNAME))
            tv_please_login.visibility = View.GONE
            rv_quit_login.visibility = View.VISIBLE
            rl_collect.visibility = View.VISIBLE
        }else{
            tv_user_name.setText(StringUtils.getString(R.string.string_tour)+ Random.nextInt(1,999))
            tv_please_login.visibility = View.VISIBLE
            rv_quit_login.visibility = View.GONE
            rl_collect.visibility = View.GONE
        }
    }

    override fun loginOutSuccess() {
        super.loginOutSuccess()
        setLoginStatus()
    }


}