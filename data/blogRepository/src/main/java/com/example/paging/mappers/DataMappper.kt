package com.example.paging.mappers

import com.example.local.model.Blog
import com.example.local.model.Owner
import com.example.network.model.BlogDTO
import com.example.network.model.OwnerDTO


fun List<BlogDTO?>.toDomain():List<Blog>{
    return map {
        Blog(it?.id ?: "",
            it?.image ?: "",
            it?.likes?:0,
            it?.owner?.toDomain()?: Owner("","","","",""),
            it?.publishDate?:"",
            it?.tags?: emptyList(),
            it?.text?:""
        )
    }
}

fun OwnerDTO.toDomain(): Owner {
    return Owner(firstName, id, lastName, picture, title)
}