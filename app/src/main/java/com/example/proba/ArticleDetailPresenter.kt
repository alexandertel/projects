package com.example.proba

import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleDetailPresenter(val view: IArticleView) {

    fun loadArticle(id:String) {
        GlobalScope.launch(Dispatchers.IO) {
            val articleDetail = AppComponent.api.getArticleDetail(id)
            launch(Dispatchers.Main)
            {
               view.showArticleDetail(articleDetail)

            }

        }
    }
}