package com.example.proba.di

import com.example.proba.blogs.BlogDetailFragment
import com.example.proba.blogs.FragmentBlogList
import com.example.proba.data.ApiService
import com.example.proba.data.MyDatabase
import com.example.proba.detail.ArticleDetailFragment
import com.example.proba.list.FragmentArticleList
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun api(): ApiService
    fun db(): MyDatabase
    fun inject(fragment: FragmentArticleList)
    fun inject(fragment: ArticleDetailFragment)
    fun inject(fragment: FragmentBlogList)
    fun inject(fragment: BlogDetailFragment)
}
