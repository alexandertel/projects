package com.example.proba.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.domain.ArticleInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleDetailViewModel @Inject constructor(
    private val interactor: ArticleInteractor
) : ViewModel() {

    val articleDetailState = MutableLiveData<DetailState>()

    fun loadArticle(articleId: String) {
        articleDetailState.value = DetailState.Loading
        viewModelScope.launch {
            val articleDetail =
                withContext(Dispatchers.IO) { interactor.getArticleDetail(articleId) }

            articleDetailState.value = when (articleDetail) {
                null -> DetailState.Error
                else -> DetailState.Success(articleDetail)
            }
        }
    }
}
