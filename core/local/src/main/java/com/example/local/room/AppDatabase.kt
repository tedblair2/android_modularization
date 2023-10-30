package com.example.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.model.Blog
import com.example.local.model.BlogKey

@Database(entities = [Blog::class, BlogKey::class], version = 1, exportSchema = false)
@TypeConverters(DbConverter::class)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getBlogDao(): BlogDao
}