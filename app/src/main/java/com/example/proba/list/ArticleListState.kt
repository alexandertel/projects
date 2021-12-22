package com.example.proba.list

import com.example.proba.data.Article

sealed class ArticleListState {
    class Success(val articles: List<Article>) : ArticleListState()
    object Error : ArticleListState()
    object Loading : ArticleListState()
}
