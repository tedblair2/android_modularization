package com.example.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "blog_table")
data class Blog(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String?,
    val likes: Int?,
    @Embedded
    val owner: Owner?,
    val publishDate: String?,
    val tags: List<String?>?,
    val text: String?
)