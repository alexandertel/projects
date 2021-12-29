package com.example.proba.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.data.ApiService
import com.example.proba.data.Article
import com.example.proba.data.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val db: MyDatabase,
    private val api: ApiService
) : ViewModel() {

    val articlesViewState = MutableLiveData<ArticleListState>()

    fun loadArticles() {
        articlesViewState.value = ArticleListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            var articles: List<Article>?

            try {
                articles = api.getArticles()
                db.articlesDAO().clear()
                db.articlesDAO().insertAll(articles)
            } catch (e: Exception) {
                articles = db.articlesDAO().getAll()
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
