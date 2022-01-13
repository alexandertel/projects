package com.example.proba.blogs

import com.example.proba.data.BlogDetail

sealed class BlogDetailState {
    class Success(val blogDetail: BlogDetail) : BlogDetailState()
    object Error : BlogDetailState()
    object Loading : BlogDetailState()
}
