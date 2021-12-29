package com.example.proba.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.proba.MyApplication
import com.example.proba.R
import com.example.proba.core.BaseFragment
import com.example.proba.data.ArticleDetail
import kotlinx.android.synthetic.main.fragment_article.*
import javax.inject.Inject

class ArticleDetailFragment : BaseFragment() {

    var id: String
        get() = arguments?.getString(ARTICLE_ID, "").orEmpty()
        set(value) {
            val b = Bundle()
            b.putString(ARTICLE_ID, value)
            arguments = b
        }

    override var layoutResource = R.layout.fragment_article

    @Inject
    lateinit var factoryDetails: DetailViewModelFactory

    private val viewModel: ArticleDetailViewModel by viewModels(factoryProducer = {
        factoryDetails
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyApplication.component.inject(this)

        viewModel.articleDetailState.observe(viewLifecycleOwner, Observer<DetailState> { state ->
            handleDetailState(state)
        })
        viewModel.loadArticle(id)
        tv_retry_detail_button.setOnClickListener {
            viewModel.loadArticle(id)
        }
    }

    private fun handleDetailState(state: DetailState) = when (state) {
        is DetailState.Success -> showArticleDetail((state.articleDetail))
        is DetailState.Error -> {
            error_detail_view.isVisible = true
            tv_detail_progressBar.isVisible = false
        }
        is DetailState.Loading -> {
            tv_detail_progressBar.isVisible = true
            error_detail_view.isVisible = false
        }
    }

    private fun showArticleDetail(article: ArticleDetail) {
        tv_detail_progressBar.isVisible = false
        error_detail_view.isVisible = false
        tv_article_name.text = article.title
        tv_article_text.text = article.summary
        Glide.with(requireContext())
            .load(article.imageUrl)
            .centerCrop()
            .into(iv_article_picture)
    }

    companion object {
        private const val ARTICLE_ID = "id"
    }
}
