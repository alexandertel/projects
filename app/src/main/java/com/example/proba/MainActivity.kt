package com.example.proba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.proba.blogs.FragmentBlogList
import com.example.proba.list.FragmentArticleList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_article_button.setOnClickListener {
            showFragment(FragmentArticleList())
        }
        tv_blog_button.setOnClickListener {
            showFragment(FragmentBlogList())
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.bigMT, fragment)
            .commit()
    }
}
