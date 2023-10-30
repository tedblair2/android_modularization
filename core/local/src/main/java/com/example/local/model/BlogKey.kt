package com.example.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_keys")
data class BlogKey(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prev:Int?,
    val next:Int?
)
