package com.example.local.model

import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    val firstName: String?,
    val ownerid: String?,
    val lastName: String?,
    val picture: String?,
    val title: String?
)