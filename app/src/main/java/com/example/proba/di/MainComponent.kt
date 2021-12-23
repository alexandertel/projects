package com.example.proba.di

import com.example.proba.data.ApiService
import com.example.proba.data.MyDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun api(): ApiService
    fun db(): MyDatabase
}
