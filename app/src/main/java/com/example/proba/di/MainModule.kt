package com.example.proba.di

import android.content.Context
import androidx.room.Room
import com.example.proba.data.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): MyDatabase {
        return Room
            .databaseBuilder(context, MyDatabase::class.java, "basaList")
            .build()
    }
}
