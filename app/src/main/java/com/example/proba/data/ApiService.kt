package com.example.proba.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("articles")
    suspend fun getArticles(): List<Article>

    @GET("articles/{id}")
    suspend fun getArticleDetail(@Path("id") articleId: String): ArticleDetail

    @GET("blogs")
    suspend fun getBlogs(): List<Blog>

    @GET("blogs/{id}")
    suspend fun getBlogDetail(@Path("id") blogId: String): BlogDetail
}
