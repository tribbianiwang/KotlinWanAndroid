package com.wl.wanandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatViewInflater
import androidx.fragment.app.Fragment
import com.wl.wanandroid.R
import com.wl.wanandroid.activity.LoginActivity
import com.wl.wanandroid.utils.ImmerBarUtils
import kotlinx.android.synthetic.main.layout_fragment_mine.*

class MineFragment : BaseFragment() {

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
        tv_please_login.setOnClickListener {
            startActivity(Intent(activity,LoginActivity::class.java))
        }

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmerBarUtils.initImmerBar(this,R.color.white)
    }

}