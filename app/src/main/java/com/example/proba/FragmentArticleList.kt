package com.example.proba

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_article_list.*


class FragmentArticleList : BaseFragment() /* ,IArticleListView*/ {   // Создаем фрагмент FragmentArticleList наследуемый BaseFragment

    lateinit var adapter1: AdapterList  // отложенная инициализация переменной  adapter1 класса AdapterList

    override var layoutResource = R.layout.fragment_article_list   // присваеваем переменной значение макета fragment_article_list

   //    private val presenter = ArticleListPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {    // создаем вьюшку
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ArticleListViewModel =                  //
            ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        viewModel.articlesViewState.observe(this, Observer<List<Article>> { articles ->
            showArticles(articles)
        })
        viewModel.loadArticles()
        //        presenter.loadArticles()
    }

    private fun showArticles(articles: List<Article>) {
        adapter1 = AdapterList(
            articles,
            clickItem = { articleId ->
                val fragment = FragmentArticle()
                fragment.id = articleId

                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.bigMT, fragment).commit()
            }
        )
        recyclerView.adapter = adapter1
    }

//    override fun showArticles(articles: List<Article>) {
//        adapter1 = AdapterList(
//            articles,
//            clickItem = { articleId ->
//                val fragment = FragmentArticle()
//                fragment.id = articleId
//
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .addToBackStack(null)
//                    .add(R.id.bigMT, fragment).commit()
//            }
//        )
//        rv.adapter = adapter1
//    }
//
//    override fun showError(string: String) {
//        Toast.makeText(requireContext(),string, Toast.LENGTH_SHORT).show()
//    }
}
