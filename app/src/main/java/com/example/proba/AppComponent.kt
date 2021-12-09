package com.example.proba

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppComponent {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spaceflightnewsapi.net/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)
}