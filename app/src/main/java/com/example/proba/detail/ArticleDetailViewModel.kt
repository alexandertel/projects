package com.example.proba.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.MyApplication
import com.example.proba.data.ApiService
import com.example.proba.data.ArticleDetail
import com.example.proba.data.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleDetailViewModel @Inject constructor(
    private val db: MyDatabase,
    private val api: ApiService
) : ViewModel() {

    val articleDetailState = MutableLiveData<DetailState>()

    fun loadArticle(articleId: String) {
        articleDetailState.value = DetailState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            var articleDetail: ArticleDetail?

            try {
                articleDetail = api.getArticleDetail(articleId)
                db.articleDetailDAO().insertDetail(articleDetail)
            } catch (e: Exception) {
                articleDetail = db.articleDetailDAO().getDetail(articleId)
            }
            launch(Dispatchers.Main) {
                articleDetailState.value = when (articleDetail) {
                    null -> DetailState.Error
                    else -> DetailState.Success(articleDetail)
                }
            }
        }
    }
}
