package com.example.proba.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.MyApplication
import com.example.proba.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArticleListViewModel : ViewModel() {

    private var _articlesViewState = MutableLiveData<ArticleListState>()

    fun articlesViewState() = _articlesViewState

    fun loadArticles() {
        _articlesViewState.value = ArticleListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            var articles: List<Article>?

            try {
                articles = MyApplication.component.api().getArticles()
                MyApplication.component.db().articlesDAO().clear()
                MyApplication.component.db().articlesDAO().insertAll(articles)
            } catch (e: Exception) {
                articles = MyApplication.component.db().articlesDAO().getAll()
            }
            launch(Dispatchers.Main) {
                _articlesViewState.value = when (articles) {
                    null -> ArticleListState.Error
                    else -> ArticleListState.Success(articles)
                }
            }
        }
    }
}
