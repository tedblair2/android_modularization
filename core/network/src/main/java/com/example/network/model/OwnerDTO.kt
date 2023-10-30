package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OwnerDTO(
    val firstName: String?,
    val id: String?,
    val lastName: String?,
    val picture: String?,
    val title: String?
)