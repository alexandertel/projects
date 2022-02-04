package com.example.proba.blogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.domain.BlogInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BlogListViewModel @Inject constructor(
    private val interactor: BlogInteractor
) : ViewModel() {

    val blogsViewState = MutableLiveData<BlogListState>()

    fun loadBlogs() {
        blogsViewState.value = BlogListState.Loading
        viewModelScope.launch {
            val blogs = withContext(Dispatchers.IO) {interactor.getBlogs()}

            blogsViewState.value = when (blogs) {
                null -> BlogListState.Error
                else -> BlogListState.Success(blogs)
            }
        }
    }
}
