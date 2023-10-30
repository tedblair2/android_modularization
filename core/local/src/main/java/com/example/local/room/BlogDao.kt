package com.example.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.local.model.Blog
import com.example.local.model.BlogKey

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogs:List<Blog>)

    @Query("SELECT * FROM blog_table")
    fun getAllBlogs():PagingSource<Int,Blog>

    @Query("DELETE FROM blog_table")
    suspend fun deleteBlogs()


    @Query("SELECT * FROM blog_keys where id=:id")
    suspend fun getRemoteKey(id:String): BlogKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(keys:List<BlogKey>)

    @Query("DELETE FROM blog_keys")
    suspend fun deleteRemoteKeys()


    @Transaction
    suspend fun pagingTransaction(keys:List<BlogKey>, blogs: List<Blog>, isRefresh:Boolean){
        if (isRefresh){
            deleteBlogs()
            deleteRemoteKeys()
        }
        insertRemoteKeys(keys)
        insertBlogs(blogs)
    }
}