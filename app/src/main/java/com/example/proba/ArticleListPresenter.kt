package com.example.proba

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleListPresenter(val view: IArticleListView) {

    var articles: List<Article> = emptyList()

    fun loadArticles() {
        if (articles.isNotEmpty()) {
            view.showArticles(articles)
            return
        }

        GlobalScope.launch(Dispatchers.IO) {
            val listArticles = AppComponent.api.getArticles()
            articles = listArticles
            launch(Dispatchers.Main) {
                view.showArticles(listArticles)
            }
        }
    }
}