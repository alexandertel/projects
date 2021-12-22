package com.example.proba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proba.list.FragmentArticleList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.bigMT, FragmentArticleList()).commit()
    }
}
