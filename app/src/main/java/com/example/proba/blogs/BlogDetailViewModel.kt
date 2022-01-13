package com.example.proba.blogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.data.ApiService
import com.example.proba.data.BlogDetail
import com.example.proba.data.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class BlogDetailViewModel @Inject constructor(
    private val db: MyDatabase,
    private val api: ApiService
) : ViewModel() {

    val blogDetailState = MutableLiveData<BlogDetailState>()

    fun loadBlog(blogId: String) {
        blogDetailState.value = BlogDetailState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            var blogDetail: BlogDetail?

            try {
                blogDetail = api.getBlogDetail(blogId)
                db.blogDetailDAO().insertBlogDetail(blogDetail)
            } catch (e: Exception) {
                blogDetail = db.blogDetailDAO().getBlogDetail(blogId)
            }
            launch(Dispatchers.Main) {
                blogDetailState.value = when (blogDetail) {
                    null -> BlogDetailState.Error
                    else -> BlogDetailState.Success(blogDetail)
                }
            }
        }
    }
}
