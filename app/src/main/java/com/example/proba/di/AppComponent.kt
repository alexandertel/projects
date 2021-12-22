package com.example.proba.di

import androidx.room.Room
import com.example.proba.MyApplication
import com.example.proba.data.MyDatabase
import com.example.proba.data.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppComponent {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spaceflightnewsapi.net/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)

    val db by lazy {
        Room
            .databaseBuilder(
                MyApplication.context ?: error("oops"),
                MyDatabase::class.java,
                "basaList"
            )
            .build()
    }
}
