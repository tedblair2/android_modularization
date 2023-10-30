package com.example.paging.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.local.model.Blog
import com.example.local.repository.DbService
import com.example.local.room.BlogDao
import com.example.network.repository.ApiService
import com.example.paging.paging.BlogsMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BlogsRepositoryImpl @Inject constructor(
    private val dbService: DbService,
    private val apiService: ApiService
) : BlogsRepository {

    override fun getBlogs(): Flow<PagingData<Blog>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = BlogsMediator(dbService, apiService),
            pagingSourceFactory = {
                dbService.getBlogs()
            }
        ).flow
    }
}