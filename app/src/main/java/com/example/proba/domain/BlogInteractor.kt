package com.example.proba.domain

import com.example.proba.data.*
import kotlinx.coroutines.delay

interface BlogInteractor {
    suspend fun getBlogs(): List<Blog>?
    suspend fun getBlogDetail(blogId: String): BlogDetail?
}

class BlogInteractorImpl(
    private val repository: BlogRepository
) : BlogInteractor {

    override suspend fun getBlogs(): List<Blog>? {
        delay(200)
        var blogs: List<Blog>?

        try {
            blogs = repository.getLocalBlogs()
            repository.clearLocalBlogs()
            repository.saveLocalBlogs(blogs)
        } catch (e: Exception) {
            blogs = repository.getLocalBlogs()
        }
        return blogs
    }

    override suspend fun getBlogDetail(blogId: String): BlogDetail? {
        delay(200)
        var blogDetail: BlogDetail?

        try {
            blogDetail = repository.downloadABlogDetails(blogId)
            repository.saveLocalBlogDetails(blogDetail)
        } catch (e: Exception) {
            blogDetail = repository.getLocalBlogDetail(blogId)
        }
        return blogDetail
    }
}
