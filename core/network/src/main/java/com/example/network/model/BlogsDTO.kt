package com.example.network.model

import com.example.network.model.BlogDTO
import kotlinx.serialization.Serializable

@Serializable
data class BlogsDTO(
    val data: List<BlogDTO?>?,
    val limit: Int?,
    val page: Int?,
    val total: Int?
)