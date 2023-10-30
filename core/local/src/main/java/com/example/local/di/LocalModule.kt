package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.repository.DbService
import com.example.local.repository.DbServiceImpl
import com.example.local.room.AppDatabase
import com.example.local.room.BlogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mods_app_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(appDatabase: AppDatabase):BlogDao=appDatabase.getBlogDao()

    @Provides
    fun provideDbService(blogDao: BlogDao):DbService=DbServiceImpl(blogDao)
}