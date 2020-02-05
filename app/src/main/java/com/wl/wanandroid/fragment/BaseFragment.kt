package com.wl.wanandroid.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.billy.android.loading.Gloading
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.GlobalAdapter
import com.wl.wanandroid.dialog.AlertDialog
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.AppConstants.USERLOGINBROADCAST
import com.wl.wanandroid.utils.AppConstants.USERLOGOUTBROADCAST
import com.wl.wanandroid.utils.LogUtils
import com.wl.wanandroid.utils.StringUtils
import com.wl.wanandroid.utils.T


open class BaseFragment : ImmersionFragment() {

     lateinit  var mContext: Activity

    // 防暴力点击
    protected var lastClickTime: Long = 0

    protected var mHolder: Gloading.Holder? = null

    private var connectivityManager: ConnectivityManager? = null
    private var info: NetworkInfo? = null

    private val fragmentBroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                USERLOGINBROADCAST -> loginSuccess()

                USERLOGOUTBROADCAST -> loginOutSuccess()

                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    connectivityManager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
            AppConstants.QUERYSTATUSLOADING -> showProgress(activity)

            AppConstants.QUERYSTATUSSUCCESS -> hideProgress()

            AppConstants.QUERYSTATUSFAILED -> hideProgress()
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
        LogUtils.d(TAG, "demoerror" + s!!)


            T.showShort(activity!!, s)


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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return super.onCreateView(inflater, container, savedInstanceState)

    }



    override fun onAttach(context: Context) {

        super.onAttach(context)
        (context as Activity)?.let { mContext = it }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val intentFilter = IntentFilter()
        intentFilter.addAction(USERLOGINBROADCAST)
        intentFilter.addAction(USERLOGOUTBROADCAST)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        mContext.registerReceiver(fragmentBroadcastReceiver, intentFilter)

        Gloading.initDefault(GlobalAdapter())

    }

    protected fun initLoadingStatusView(rootView: View): Gloading.Holder {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(rootView).withRetry { onLoadRetry() }
        }
        return mHolder!!
    }


    open fun onLoadRetry() {
        // override this method in subclass to do retry task
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

    override fun onDestroyView() {
        super.onDestroyView()
        mContext.unregisterReceiver(fragmentBroadcastReceiver)

        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
        dialog = null
    }

    override fun initImmersionBar() {

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