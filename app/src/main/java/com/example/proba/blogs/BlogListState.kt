package com.example.proba.blogs

import com.example.proba.data.Blog


sealed class BlogListState {
    class Success(val blogs: List<Blog>) : BlogListState()
    object Error : BlogListState()
    object Loading : BlogListState()
}
