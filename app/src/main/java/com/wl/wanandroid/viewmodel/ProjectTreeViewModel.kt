package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.ProjectTreeBean
import com.wl.wanandroid.model.ProjectTreeModel

class ProjectTreeViewModel:BaseViewModel<ProjectTreeBean>() {
    var systemTreeModel = ProjectTreeModel(this)
    init {
        baseModel = systemTreeModel
    }

    fun getSystemTree(){
        systemTreeModel.getSystemTrees()
    }
}