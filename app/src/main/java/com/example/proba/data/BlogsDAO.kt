package com.example.proba.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogsDAO {

    @Query("SELECT * FROM blog")
    fun getBlogs(): List<Blog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBlogs(blogs: List<Blog>)

    @Query("DELETE FROM blog")
    fun clearBlogs()
}
