package com.example.network.repository

import com.example.network.ApiResult
import com.example.network.model.BlogsDTO

interface ApiService {
    suspend fun getBlogs(page:Int):ApiResult<BlogsDTO>
}