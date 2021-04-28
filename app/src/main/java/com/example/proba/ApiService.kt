package com.example.proba

import retrofit2.http.GET

interface ApiService {
    @GET("api/v2/articles")
    suspend fun getArticles(): List<Article>
}