package com.example.proba.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDetailDAO {

    @Query("SELECT * FROM blogdetail WHERE id = :blogId ")
    fun getBlogDetail(blogId: String): BlogDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBlogDetail(blogDetail: BlogDetail)
}
