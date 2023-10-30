package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BlogDTO(
    val id: String?,
    val image: String?,
    val likes: Int?,
    val owner: OwnerDTO?,
    val publishDate: String?,
    val tags: List<String?>?,
    val text: String?
)