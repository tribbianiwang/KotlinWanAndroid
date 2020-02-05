package com.wl.wanandroid.view

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.billy.android.loading.Gloading.*

import android.graphics.drawable.AnimationDrawable
import android.widget.RelativeLayout
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.NetworkUtil.isNetworkConnected
import kotlinx.android.synthetic.main.alert_dialog.view.*
import kotlinx.android.synthetic.main.view_global_loading_status.view.*


class GlobalLoadingStatusView :LinearLayout,View.OnClickListener{


    private val mTextView: TextView
    private val mRetryTask: Runnable?
    private val mImageView: ImageView
    private val rlLoading : RelativeLayout
    private val llEmptyError:LinearLayout


    constructor(context:Context,retryTask:Runnable) : super(context) {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.view_global_loading_status, this, true)
        rlLoading = rl_loading
        mImageView = image
        mTextView = text
        llEmptyError = ll_error_empty
        this.mRetryTask = retryTask
        setBackgroundColor(-0xf0f10)
    }

    fun setMsgViewVisibility(visible: Boolean) {
        mTextView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setStatus(status: Int) {
        var show = true
        var onClickListener: View.OnClickListener? = null
        var image:Int = R.drawable.icon_placeholder

        var str = R.string.str_none
        when (status) {
            STATUS_LOAD_SUCCESS -> {
                isShowLoading(false)
                show = false
            }
            STATUS_LOADING -> {
                str = R.string.loading
              isShowLoading(true)
            }

            STATUS_LOAD_FAILED -> {
                str = R.string.load_failed
                image = R.drawable.icon_loading_failed
                val networkConn = isNetworkConnected(context)
                if (networkConn != null && (!networkConn)!!) {
                    str = R.string.load_failed_no_network
                    image = R.drawable.icon_no_wifi
                }
                onClickListener = this
                isShowLoading(false)
            }
            STATUS_EMPTY_DATA -> {
                str = R.string.empty
                image = R.drawable.icon_empty
                isShowLoading(false)
            }
            else -> {
            }
        }

            mImageView.setImageResource(image)


        setOnClickListener(onClickListener)
        mTextView.setText(str)
        visibility = if (show) View.VISIBLE else View.GONE
    }
    override fun onClick(v: View?) {
        mRetryTask?.run()
    }

    fun isShowLoading(isShowLoading:Boolean){
        llEmptyError.visibility = if(isShowLoading)GONE else VISIBLE
        rlLoading.visibility=if(isShowLoading)VISIBLE else GONE

    }

}