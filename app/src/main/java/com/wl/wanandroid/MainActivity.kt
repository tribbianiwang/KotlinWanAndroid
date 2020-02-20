package com.wl.wanandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.wl.wanandroid.adapter.VpMainAdapter
import com.wl.wanandroid.fragment.HomeFragment
import com.wl.wanandroid.fragment.MineFragment
import com.wl.wanandroid.fragment.ProjectFragment
import com.wl.wanandroid.fragment.SystemFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import com.wl.wanandroid.activity.BaseActivity
import com.wl.wanandroid.utils.T



class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //testcommit
        //firstcommit



        var fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())
        fragments.add(SystemFragment())
        fragments.add(MineFragment())

        vp_main.adapter = VpMainAdapter(supportFragmentManager, fragments)
        vp_main.offscreenPageLimit = 3

        tablayout_main.addTab(tablayout_main.newTab().setCustomView(getItemView(getString(R.string.string_home),
            R.drawable.item_tablayout_main_home_icon_selector
        )))
        tablayout_main.addTab(tablayout_main.newTab().setCustomView(getItemView(getString(R.string.string_project),
            R.drawable.item_tablayout_main_project_icon_selector
        )))
        tablayout_main.addTab(tablayout_main.newTab().setCustomView(getItemView(getString(R.string.string_system),
            R.drawable.item_tablayout_main_system_icon_selector
        )))
        tablayout_main.addTab(tablayout_main.newTab().setCustomView(getItemView(getString(R.string.string_mine),
            R.drawable.item_tablayout_main_mine_icon_selector
        )))

        tablayout_main.setSelectedTabIndicatorColor(Color.TRANSPARENT)
        vp_main.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout_main))
        tablayout_main.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(vp_main))

    }


    private fun getItemView(titleName: String, imageViewId: Int): View {
        val itemView = LayoutInflater.from(this).inflate(R.layout.item_tablayout_main_layout, null)
        val tvItem = itemView.findViewById<TextView>(R.id.tv_tab_name)
        val ivTabItem = itemView.findViewById<ImageView>(R.id.iv_tab_icon)
        tvItem.text = titleName
        ivTabItem.setImageResource(imageViewId)



        return itemView

    }

}
