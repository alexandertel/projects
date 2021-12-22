package com.example.proba.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.data.ArticleDetail
import com.example.proba.di.AppComponent
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
            var onlineArticleDetail: ArticleDetail? = null
            var offlineArticleDetail: ArticleDetail? = null
            try {
                onlineArticleDetail = AppComponent.api.getArticleDetail(articleId)
                AppComponent.db.articleDetailDAO().insertDetail(onlineArticleDetail)
            } catch (e: Exception) {
                offlineArticleDetail = AppComponent.db.articleDetailDAO().getDetail(articleId)
            }
            launch(Dispatchers.Main) {
                _articleDetailState.value = when {
                    onlineArticleDetail == null && offlineArticleDetail == null ->
                        DetailState.Error
                    onlineArticleDetail != null || offlineArticleDetail != null ->
                        DetailState.Success(
                            onlineArticleDetail ?: offlineArticleDetail!!
                        )
                    else -> _articleDetailState.value
                }
            }
        }
    }
}
