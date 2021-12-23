package com.example.proba.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.MyApplication
import com.example.proba.data.ArticleDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArticleDetailViewModel : ViewModel() {

    private val _articleDetailState = MutableLiveData<DetailState>()

    fun articleDetailState() = _articleDetailState

    fun loadArticle(articleId: String) {
        _articleDetailState.value = DetailState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            var articleDetail: ArticleDetail?

            try {
                articleDetail = MyApplication.component.api().getArticleDetail(articleId)
                MyApplication.component.db().articleDetailDAO().insertDetail(articleDetail)
            } catch (e: Exception) {
                articleDetail = MyApplication.component.db().articleDetailDAO().getDetail(articleId)
            }
            launch(Dispatchers.Main) {
                _articleDetailState.value = when (articleDetail) {
                    null -> DetailState.Error
                    else -> DetailState.Success(articleDetail)
                }
            }
        }
    }
}
