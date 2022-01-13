package com.example.proba.blogs

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.proba.MyApplication
import com.example.proba.R
import com.example.proba.core.BaseFragment
import com.example.proba.data.BlogDetail
import kotlinx.android.synthetic.main.fragment_blog.*
import javax.inject.Inject

class BlogDetailFragment : BaseFragment() {

    var id: String
        get() = arguments?.getString(BLOG_ID, "").orEmpty()
        set(value) {
            val b = Bundle()
            b.putString(BLOG_ID, value)
            arguments = b
        }

    override var layoutResource = R.layout.fragment_blog

    @Inject
    lateinit var factoryDetails: BlogDetailViewModelFactory

    private val viewModel: BlogDetailViewModel by viewModels(factoryProducer = {
        factoryDetails
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyApplication.component.inject(this)

        viewModel.blogDetailState.observe(viewLifecycleOwner, Observer<BlogDetailState> { state ->
            handleDetailState(state)
        })
        viewModel.loadBlog(id)
        tv_retry_blog_detail_button.setOnClickListener {
            viewModel.loadBlog(id)
        }
    }

    private fun handleDetailState(state: BlogDetailState) = when (state) {
        is BlogDetailState.Success -> showBlogDetail((state.blogDetail))
        is BlogDetailState.Error -> {
            error_blog_detail_view.isVisible = true
            tv_blog_detail_progressBar.isVisible = false
        }
        is BlogDetailState.Loading -> {
            tv_blog_detail_progressBar.isVisible = true
            error_blog_detail_view.isVisible = false
        }
    }

    private fun showBlogDetail(blog: BlogDetail) {
        tv_blog_detail_progressBar.isVisible = false
        error_blog_detail_view.isVisible = false
        tv_blog_name.text = blog.title
        tv_blog_text.text = blog.summary
        Glide.with(requireContext())
            .load(blog.imageUrl)
            .centerCrop()
            .into(iv_blog_picture)
    }

    companion object {
        private const val BLOG_ID = "id"
    }
}
