package com.wl.wanandroid.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.billy.android.loading.Gloading
import com.wl.wanandroid.R
import com.wl.wanandroid.dialog.AlertDialog
import com.wl.wanandroid.fragment.BaseFragment
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.utils.T

open class BaseActivity: AppCompatActivity() {

    protected var mHolder: Gloading.Holder? = null

    // 防暴力点击
    protected var lastClickTime: Long = 0
    private var connectivityManager: ConnectivityManager? = null
    private var info: NetworkInfo? = null

    private val fragmentBroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                AppConstants.USERLOGINBROADCAST -> loginSuccess()

                AppConstants.USERLOGOUTBROADCAST -> loginOutSuccess()

                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    info = connectivityManager!!.activeNetworkInfo
                    if (info != null && info!!.isAvailable) {
                        val name = info!!.typeName
                        netWorkSuccess()

                    } else {

                        netWorkFailed()
                    }
                }
            }

        }
    }
    private var hasNetWorkFailed = false


    var queryStatusObserver: Observer<String> = Observer { status ->
        when (status) {
            AppConstants.QUERYSTATUSLOADING -> BaseFragment.showProgress(this)

            AppConstants.QUERYSTATUSSUCCESS -> BaseFragment.hideProgress()

            AppConstants.QUERYSTATUSFAILED -> BaseFragment.hideProgress()
        }
    }


    var gLoadingqueryStatusObserver: Observer<String> = Observer { status ->
        when (status) {
            AppConstants
                .QUERYSTATUSLOADING -> showLoading()

            AppConstants.QUERYSTATUSSUCCESS -> showLoadSuccess()

            AppConstants.QUERYSTATUSFAILED -> showLoadFailed()
            AppConstants.QUERYSTATUSEMPTY ->

                showEmpty()
        }
    }


    var errorMsgObserver: Observer<String> = Observer { s ->


        T.showShort(this, s)


    }

    open fun loginOutSuccess() {

    }

    open fun loginSuccess() {

    }

    fun netWorkSuccess() {
        if (hasNetWorkFailed) {
            onLoadRetry()
            hasNetWorkFailed = false
        }

    }

    fun netWorkFailed() {
        hasNetWorkFailed = true

    }

    fun showLoading() {
        //        initLoadingStatusViewIfNeed();
        if (mHolder != null) {
            mHolder!!.showLoading()
        }


    }

    fun showLoadSuccess() {
        //        initLoadingStatusViewIfNeed();
        if (mHolder != null) {
            mHolder!!.showLoadSuccess()
        }

    }

    fun showLoadFailed() {
        //        initLoadingStatusViewIfNeed();
        if (mHolder != null) {
            mHolder!!.showLoadFailed()
        }

    }

    fun showEmpty() {
        if (mHolder != null) {
            mHolder!!.showEmpty()
        }
        //        initLoadingStatusViewIfNeed();

    }


    open fun onLoadRetry() {
        // override this method in subclass to do retry task
    }
    /**
     * 防暴力点击 上次点击时间, lastClickTime 本次点击时间, time 时间差, timeD 多长时间内点击无效, timelong
     */
    protected fun isFastDoubleClick(timelong: Int): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime
        if (timeD > timelong) {
            lastClickTime = time
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(fragmentBroadcastReceiver)

        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
        dialog = null
    }



    companion object {
        private val TAG = "BaseFragment"


        var dialog: AlertDialog? = null

        /**
         * 显示等待对话框，一般用于网络请求的时候
         *
         * @param context
         */
        fun showProgress(context: Context?) {
            if (context != null && !(context as Activity).isFinishing) {
                if (dialog == null) {
                    dialog = AlertDialog(context, R.style.FinalsDialog)
                }
                dialog!!.show()
            }
        }


        /**
         * 关闭等待对话框
         */
        fun hideProgress() {
            if (dialog != null) {
                dialog!!.dismiss()
                dialog = null
            }
        }
    }
}