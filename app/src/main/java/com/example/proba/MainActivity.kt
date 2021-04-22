package com.example.proba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<View>(R.id.button).setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.bigMT, Fragment3()).commit()

//        }


    }
}
