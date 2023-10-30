package com.example.local.repository

import androidx.paging.PagingSource
import com.example.local.model.Blog
import com.example.local.model.BlogKey

interface DbService {
    fun getBlogs():PagingSource<Int,Blog>
    suspend fun getBlogKey(id:String):BlogKey
    suspend fun pagingTransaction(blogs:List<Blog>,keys:List<BlogKey>,isRefresh:Boolean)
}