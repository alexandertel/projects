package com.example.proba.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Article::class, ArticleDetail::class, Blog::class, BlogDetail::class],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun articlesDAO(): ArticlesDAO
    abstract fun articleDetailDAO(): ArticleDetailDAO
    abstract fun blogsDAO(): BlogsDAO
    abstract fun blogDetailDAO(): BlogDetailDAO
}
