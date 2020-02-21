package com.wl.wanandroid.dao

import androidx.room.*

@Dao
interface SearchHistoryDao {
    //插入一条历史搜索记录
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchHistory(searchHistoryKeyDaoBean: SearchHistoryKeyDaoBean)

    //获取所有的历史搜索记录
    @Query("SELECT * FROM SearchHistoryKeys")
    fun getAllSearchHistory():List<SearchHistoryKeyDaoBean>


    //清空历史搜索记录
    @Query("DELETE  FROM SearchHistoryKeys")
    fun deleteAllSearchHistory()




}