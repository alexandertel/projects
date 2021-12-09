package com.example.proba

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleListViewModel : ViewModel() {   // создаем класс

    var articlesViewState =
        MutableLiveData<List<Article>>()   // класс содержащий данные и который можно наблюдать

    fun loadArticles() {
        GlobalScope.launch(Dispatchers.IO) {
            var listArticles: List<Article>? = null
            try {
                listArticles = AppComponent.api.getArticles()
            } catch (e: Exception) {

// database!!!!!! DAO

            }
            launch(Dispatchers.Main) {
//                view.showArticles(listArticles)
                articlesViewState.value = listArticles.orEmpty()
            }
        }
    }
}
