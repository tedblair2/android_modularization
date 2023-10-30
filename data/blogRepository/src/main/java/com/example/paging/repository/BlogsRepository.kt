package com.example.paging.repository

import androidx.paging.PagingData
import com.example.local.model.Blog
import kotlinx.coroutines.flow.Flow

interface BlogsRepository {
    fun getBlogs():Flow<PagingData<Blog>>
}