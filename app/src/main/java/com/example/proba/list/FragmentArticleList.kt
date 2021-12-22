package com.example.proba.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.proba.R
import com.example.proba.core.BaseFragment
import com.example.proba.data.Article
import com.example.proba.detail.ArticleDetailFragment
import kotlinx.android.synthetic.main.fragment_article_list.*

class FragmentArticleList : BaseFragment() {

    private var adapter = ArticlesAdapter(
        clickItem = { articleId ->
            val fragment = ArticleDetailFragment()
            fragment.id = articleId
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.bigMT, fragment).commit()
        }
    )

    override var layoutResource = R.layout.fragment_article_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter

        val viewModel: ArticleListViewModel =
            ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        viewModel.articlesViewState.observe(this, Observer<ArticleListState> { state ->
            handleState(state)
        })
        viewModel.loadArticles()
        tv_retry_button.setOnClickListener {
            viewModel.loadArticles()
        }
    }

    private fun handleState(state: ArticleListState) = when (state) {
        is ArticleListState.Success -> showArticles((state.articles))
        is ArticleListState.Error -> {
            error_view.isVisible = true
            tv_progressBar.isVisible = false
        }
        is ArticleListState.Loading -> {
            tv_progressBar.isVisible = true
            error_view.isVisible = false
        }
    }

    private fun showArticles(articles: List<Article>) {
        tv_progressBar.isVisible = false
        error_view.isVisible = false
        adapter.submitList(articles)
    }
}
