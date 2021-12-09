package com.example.proba

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_article.*

class FragmentArticle : BaseFragment(), IArticleView {

    var id: String
        get() {
            return arguments?.getString("id", "") ?: ""
        }
        set(value) {
            val b = Bundle()
            b.putString("id", value)
            arguments = b
        }

    override var layoutResource = R.layout.fragment_article
//    var presenter = ArticleDetailPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ArticleDetailViewModel =
            ViewModelProviders.of(this).get(ArticleDetailViewModel::class.java)
        viewModel.articleDetailState.observe(this, Observer<ArticleDetail> { article ->
            showArticleDetail(article)
        })
        viewModel.loadArticle(id)
//        presenter.loadArticle(id)

    }

    override fun showArticleDetail(article: ArticleDetail) {
        tv_article_name.text = article.title
        tv_article_text.text = article.summary
        Glide.with(requireContext())
            .load(article.imageUrl)
            .centerCrop()
            .into(iv_article_picture)
    }
}
