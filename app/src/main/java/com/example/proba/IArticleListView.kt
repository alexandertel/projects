package com.example.proba

interface IArticleListView {
    fun showArticles(articles: List<Article>)
    fun showError(string: String)
}