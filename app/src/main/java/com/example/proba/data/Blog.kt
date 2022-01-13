package com.example.proba.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Blog(
    @PrimaryKey
    val id: String,
    val title: String,
    val imageUrl: String,
    val publishedAt: String
)
