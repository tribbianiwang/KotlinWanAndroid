package com.wl.wanandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wl.wanandroid.R
import com.wl.wanandroid.adapter.ProjectFragmentVpAdapter
import com.wl.wanandroid.bean.ProjectTreeBean
import com.wl.wanandroid.bean.ProjectTreeData
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.viewmodel.ProjectTreeViewModel
import kotlinx.android.synthetic.main.layout_fragment_project.*


class ProjectFragment : BaseFragment() {

    lateinit var systemTreeViewModel: ProjectTreeViewModel
    var systemFragmentVpAdapter: ProjectFragmentVpAdapter? = null
    var fragmentList: ArrayList<ProjectChildFragment> = ArrayList()
    var titles: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var contentView = inflater.inflate(R.layout.layout_fragment_project, container, false)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        systemTreeViewModel = ViewModelProviders.of(this).get(ProjectTreeViewModel::class.java)

        lifecycle.addObserver(systemTreeViewModel)

        var systemTreeObserver: Observer<ProjectTreeBean> = object : Observer<ProjectTreeBean> {
            override fun onChanged(t: ProjectTreeBean?) {
                if (t?.data != null && t?.data.size > 0) {

                        val smalldata: List<ProjectTreeData> = t?.data.subList(0,5)


                    for (single in t?.data.withIndex()) {
                        var systemChildFragment = ProjectChildFragment()
                        var bundle = Bundle()
                        bundle.putInt(AppConstants.TRANS_SYSTEM_CHILD_ID, single.value.id)
                        bundle.putString(AppConstants.TRANS_SYSTEM_CHILD_NAME, single.value.name)
                        systemChildFragment.arguments = bundle
                        fragmentList.add(systemChildFragment)
                        titles.add(single.value.name)
                    }

                    systemFragmentVpAdapter = childFragmentManager?.let { ProjectFragmentVpAdapter(fragmentList,titles, it) }

                    vp_system.setOffscreenPageLimit(5);
                    vp_system.adapter = systemFragmentVpAdapter
                    tablayout_system.setupWithViewPager(vp_system)

//                    for (single in t?.data.withIndex()) {
//
//                        tablayout_system.getTabAt(single.index)?.setText("fuck")
//                    }

                }


            }

        }

        systemTreeViewModel.baseResultLiveData.observe(this, systemTreeObserver)
        systemTreeViewModel.errorMsgLiveData.observe(this, errorMsgObserver)
        systemTreeViewModel.queryStatusLiveData.observe(this, queryStatusObserver)

        systemTreeViewModel.getSystemTree()

    }


}