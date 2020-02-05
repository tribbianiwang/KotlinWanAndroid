package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatViewInflater
import androidx.fragment.app.Fragment
import com.wl.wanandroid.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var contentView =inflater.inflate(R.layout.layout_fragment_home,container,false)

        return contentView
    }
}