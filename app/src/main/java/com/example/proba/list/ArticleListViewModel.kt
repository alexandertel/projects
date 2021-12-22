package com.example.proba.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.data.Article
import com.example.proba.di.AppComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArticleListViewModel : ViewModel() {
    var articlesViewState = MutableLiveData<ArticleListState>()

    fun loadArticles() {
        articlesViewState.value = ArticleListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            var articles: List<Article>?
            try {
                articles = AppComponent.api.getArticles()
                AppComponent.db.articlesDAO().insertAll(articles)
            } catch (e: Exception) {
                articles = AppComponent.db.articlesDAO().getAll()
            }
            launch(Dispatchers.Main) {
                articlesViewState.value = when (articles) {
                    null -> ArticleListState.Error
                    else -> ArticleListState.Success(articles)
                }
            }
        }
    }
}
