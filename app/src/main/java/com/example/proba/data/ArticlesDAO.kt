package com.example.proba

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proba.data.Article
import com.example.proba.data.ArticleDetail

@Dao
interface ArticlesDAO {

    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)
}

@Dao
interface ArticleDetailDAO {

    @Query("SELECT * FROM articledetail WHERE id = :articleId ")
    fun getDetail(articleId: String): ArticleDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(articleDetail: ArticleDetail)
}
