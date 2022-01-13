package com.example.proba.blogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.data.ApiService
import com.example.proba.data.Blog
import com.example.proba.data.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class BlogListViewModel @Inject constructor(
    private val db: MyDatabase,
    private val api: ApiService
) : ViewModel() {

    val blogsViewState = MutableLiveData<BlogListState>()

    fun loadBlogs() {
        blogsViewState.value = BlogListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            var blogs: List<Blog>?

            try {
                blogs = api.getBlogs()
                db.blogsDAO().clearBlogs()
                db.blogsDAO().insertBlogs(blogs)
            } catch (e: Exception) {
                blogs = db.blogsDAO().getBlogs()
            }
            launch(Dispatchers.Main) {
                blogsViewState.value = when (blogs) {
                    null -> BlogListState.Error
                    else -> BlogListState.Success(blogs)
                }
            }
        }
    }
}
