package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.model.SyStemTreeModel

class SystemTreeViewModel:BaseViewModel<SystemTreeBean>() {
    var systemTreeModel = SyStemTreeModel(this)
    init {
        baseModel = systemTreeModel
    }

    fun getSystemTree(){
        systemTreeModel.getSystemTrees()
    }
}