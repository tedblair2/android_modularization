package com.example.local.repository

import androidx.paging.PagingSource
import com.example.local.model.Blog
import com.example.local.model.BlogKey
import com.example.local.room.BlogDao
import javax.inject.Inject

class DbServiceImpl @Inject constructor(private val blogDao: BlogDao) : DbService {

    override fun getBlogs(): PagingSource<Int, Blog> {
        return blogDao.getAllBlogs()
    }

    override suspend fun getBlogKey(id: String): BlogKey {
        return blogDao.getRemoteKey(id)
    }

    override suspend fun pagingTransaction(blogs: List<Blog>, keys: List<BlogKey>,isRefresh:Boolean) {
        blogDao.pagingTransaction(keys, blogs,isRefresh)
    }
}