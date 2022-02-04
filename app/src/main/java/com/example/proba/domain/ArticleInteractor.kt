package com.example.proba.domain

import com.example.proba.data.Article
import com.example.proba.data.ArticleDetail
import com.example.proba.data.ArticleRepository
import kotlinx.coroutines.delay

interface ArticleInteractor {
    suspend fun getArticles(): List<Article>?
    suspend fun getArticleDetail(articleId: String): ArticleDetail?
}

class ArticleInteractorImpl(
    private val repository: ArticleRepository
) : ArticleInteractor {

    override suspend fun getArticles(): List<Article>? {
        delay(200)
        var articles: List<Article>?

        try {
            articles = repository.downloadArticles()
            repository.clearLocalArticles()
            repository.saveLocalArticles(articles)
        } catch (e: Exception) {
            articles = repository.getLocalArticles()
        }
        return articles
    }

    override suspend fun getArticleDetail(articleId: String): ArticleDetail? {
        delay(200)
        var articleDetail: ArticleDetail?

        try {
            articleDetail = repository.downloadArticleDetails(articleId)
            repository.saveLocalArticleDetails(articleDetail)
        } catch (e: Exception) {
            articleDetail = repository.getLocalArticleDetail(articleId)
        }
        return articleDetail
    }
}
