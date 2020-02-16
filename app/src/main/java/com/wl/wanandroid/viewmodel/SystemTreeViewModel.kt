package com.wl.wanandroid.viewmodel

import com.wl.wanandroid.bean.SystemTreeBean
import com.wl.wanandroid.model.SystemTreeModel

class SystemTreeViewModel:BaseViewModel<SystemTreeBean>() {
    var systemTreeModel = SystemTreeModel(this)
    init {
        baseModel = systemTreeModel
    }
    fun getSystemTree(){
        systemTreeModel.getSystemTree()
    }
}