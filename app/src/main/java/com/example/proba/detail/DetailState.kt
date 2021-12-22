package com.example.proba.detail

import com.example.proba.data.ArticleDetail

sealed class DetailState {
    class Success(val articleDetail: ArticleDetail) : DetailState()
    object Error : DetailState()
    object Loading : DetailState()
}
