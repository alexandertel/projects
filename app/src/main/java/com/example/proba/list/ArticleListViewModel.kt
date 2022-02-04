package com.example.proba.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.domain.ArticleInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val interactor: ArticleInteractor
) : ViewModel() {

    val articlesViewState = MutableLiveData<ArticleListState>()

    fun loadArticles() {
        articlesViewState.value = ArticleListState.Loading
//        viewModelScope.launch(Dispatchers.IO) {
//            val articles = interactor.getArticles()
//
//            launch(Dispatchers.Main) {
//                articlesViewState.value = when (articles) {
//                    null -> ArticleListState.Error
//                    else -> ArticleListState.Success(articles)
//                }
//            }
//        }

        viewModelScope.launch {
            val articles = withContext(Dispatchers.IO) { interactor.getArticles() }

            articlesViewState.value = when (articles) {
                null -> ArticleListState.Error
                else -> ArticleListState.Success(articles)
            }
        }
    }
}
