package com.example.proba.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Article::class, ArticleDetail::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun articlesDAO(): ArticlesDAO
    abstract fun articleDetailDAO(): ArticleDetailDAO
}
