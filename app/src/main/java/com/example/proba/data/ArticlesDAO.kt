package com.example.proba.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticlesDAO {

    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

    @Query("DELETE FROM article")
    fun clear()
}
