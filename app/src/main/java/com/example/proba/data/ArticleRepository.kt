package com.example.proba.data

interface ArticleRepository {

    suspend fun downloadArticles(): List<Article>

    suspend fun getLocalArticles(): List<Article>

    suspend fun clearLocalArticles()

    suspend fun saveLocalArticles(list: List<Article>)

    suspend fun downloadArticleDetails(articleId: String): ArticleDetail

    suspend fun getLocalArticleDetail(articleId: String): ArticleDetail?

    suspend fun saveLocalArticleDetails(articleDetail: ArticleDetail)
}

class ArticleRepositoryImpl (
    private val db: MyDatabase,
    private val api: ApiService
) : ArticleRepository {

    override suspend fun downloadArticles(): List<Article> {
        return api.getArticles()
    }

    override suspend fun getLocalArticles(): List<Article> {
        return db.articlesDAO().getAll()
    }

    override suspend fun clearLocalArticles() {
        db.articlesDAO().clear()
    }

    override suspend fun saveLocalArticles(list: List<Article>) {
        db.articlesDAO().insertAll(list)
    }

    override suspend fun downloadArticleDetails(articleId: String): ArticleDetail {
        return api.getArticleDetail(articleId)
    }

    override suspend fun getLocalArticleDetail(articleId: String): ArticleDetail? {
        return db.articleDetailDAO().getDetail(articleId)
    }

    override suspend fun saveLocalArticleDetails(articleDetail: ArticleDetail) {
        db.articleDetailDAO().insertDetail(articleDetail)
    }
}
