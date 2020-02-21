package com.wl.wanandroid.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchHistoryKeys")
 class SearchHistoryKeyDaoBean {

    constructor(searchKey:String){
        this.searchKey = searchKey
    }

    @PrimaryKey
    var searchKey:String=""
}