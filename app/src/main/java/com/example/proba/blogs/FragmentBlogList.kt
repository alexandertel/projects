package com.example.proba.blogs

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.proba.MyApplication
import com.example.proba.R
import com.example.proba.core.BaseFragment
import com.example.proba.data.Blog
import com.example.proba.detail.ArticleDetailFragment
import kotlinx.android.synthetic.main.fragment_article_list.recyclerView
import kotlinx.android.synthetic.main.fragment_blog_list.*
import javax.inject.Inject

class FragmentBlogList : BaseFragment() {

    private var adapter = BlogsAdapter(
        clickItem = { blogId ->
            val fragment = ArticleDetailFragment() //details
            fragment.id = blogId
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.bigMT, fragment).commit()
        }
    )

    override var layoutResource = R.layout.fragment_blog_list

    @Inject
    lateinit var viewModelFactory: BlogListViewModelFactory

    private val viewModel: BlogListViewModel by viewModels(factoryProducer = {
        viewModelFactory
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyApplication.component.inject(this)
        recyclerView.adapter = adapter

        viewModel.blogsViewState.observe(viewLifecycleOwner, Observer<BlogListState> { state ->
            handleState(state)
        })
        viewModel.loadBlogs()
        tv_blog_retry_button.setOnClickListener {
            viewModel.loadBlogs()
        }
    }

    private fun handleState(state: BlogListState) = when (state) {
        is BlogListState.Success -> showBlogs((state.blogs))
        is BlogListState.Error -> {
            blog_error_view.isVisible = true
            tv_blog_progressBar.isVisible = false
        }
        is BlogListState.Loading -> {
            tv_blog_progressBar.isVisible = true
            blog_error_view.isVisible = false
        }
    }

    private fun showBlogs(blogs: List<Blog>) {
        tv_blog_progressBar.isVisible = false
        blog_error_view.isVisible = false
        adapter.submitList(blogs)
    }
}
