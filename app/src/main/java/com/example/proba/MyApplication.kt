package com.example.proba

import android.app.Application
import com.example.proba.di.DaggerMainComponent
import com.example.proba.di.MainComponent
import com.example.proba.di.MainModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerMainComponent.builder().mainModule(MainModule(this)).build()
    }

    companion object {
        lateinit var component: MainComponent
    }
}
