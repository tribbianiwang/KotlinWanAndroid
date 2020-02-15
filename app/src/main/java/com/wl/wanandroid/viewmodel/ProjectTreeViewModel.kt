package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.model.ProjectTreeModel

class ProjectTreeViewModel:BaseViewModel<SystemTreeBean>() {
    var systemTreeModel = ProjectTreeModel(this)
    init {
        baseModel = systemTreeModel
    }

    fun getSystemTree(){
        systemTreeModel.getSystemTrees()
    }
}