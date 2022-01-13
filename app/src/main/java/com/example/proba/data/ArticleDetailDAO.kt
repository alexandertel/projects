package com.example.proba.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDetailDAO {

    @Query("SELECT * FROM articledetail WHERE id = :articleId ")
    fun getDetail(articleId: String): ArticleDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(articleDetail: ArticleDetail)
}
