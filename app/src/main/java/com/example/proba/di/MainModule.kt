package com.example.proba.di

import android.app.Application
import androidx.room.Room
import com.example.proba.data.*
import com.example.proba.domain.ArticleInteractor
import com.example.proba.domain.ArticleInteractorImpl
import com.example.proba.domain.BlogInteractor
import com.example.proba.domain.BlogInteractorImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MyDatabase {
        return Room
            .databaseBuilder(application, MyDatabase::class.java, "articles")
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(application: Application): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(api: ApiService, db: MyDatabase): ArticleRepository {
        return ArticleRepositoryImpl(db, api)
    }

    @Provides
    @Singleton
    fun provideBlogRepository(api: ApiService, db: MyDatabase): BlogRepository {
        return BlogRepositoryImpl(db, api)
    }

    @Provides
    @Singleton
    fun provideArticleInteractor(repository: ArticleRepository): ArticleInteractor {
        return ArticleInteractorImpl(repository)
    }

    @Provides
    @Singleton
    fun provideBlogInteractor(repository: BlogRepository): BlogInteractor {
        return BlogInteractorImpl(repository)
    }
}
