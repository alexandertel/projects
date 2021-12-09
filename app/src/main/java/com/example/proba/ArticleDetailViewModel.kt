package com.example.proba

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleDetailViewModel : ViewModel () {

    var articleDetailState = MutableLiveData<ArticleDetail>()

    fun loadArticle(articleId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val articleDetail = AppComponent.api.getArticleDetail(articleId)
            launch(Dispatchers.Main){
//                view.showArticleDetail(articleDetail)
                articleDetailState.value = articleDetail

            }

        }
    }

}