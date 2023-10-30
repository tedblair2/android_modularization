package com.example.network.repository

import com.example.network.ApiResult
import com.example.network.model.BlogsDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun getBlogs(page: Int): ApiResult<BlogsDTO> {
        return try {
            val result=httpClient.get("post"){
                parameter("page",page)
                parameter("limit",10)
            }.body<BlogsDTO>()
            ApiResult.Success(result)
        }catch (e:Exception){
            ApiResult.Error(e.message ?: "Unknown Error!!")
        }
    }
}