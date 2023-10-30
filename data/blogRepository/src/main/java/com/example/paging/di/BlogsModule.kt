package com.example.paging.di

import com.example.paging.repository.BlogsRepository
import com.example.paging.repository.BlogsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BlogsModule {

    @Binds
    abstract fun provideBlogRepository(blogsRepositoryImpl: BlogsRepositoryImpl):BlogsRepository
}