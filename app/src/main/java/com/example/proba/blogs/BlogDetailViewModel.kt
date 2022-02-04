package com.example.proba.blogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.domain.BlogInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BlogDetailViewModel @Inject constructor(
    private val interactor: BlogInteractor
) : ViewModel() {

    val blogDetailState = MutableLiveData<BlogDetailState>()

    fun loadBlog(blogId: String) {
        blogDetailState.value = BlogDetailState.Loading
        viewModelScope.launch {
            val blogDetail = withContext(Dispatchers.IO) { interactor.getBlogDetail(blogId) }

            blogDetailState.value = when (blogDetail) {
                null -> BlogDetailState.Error
                else -> BlogDetailState.Success(blogDetail)
            }
        }
    }
}
