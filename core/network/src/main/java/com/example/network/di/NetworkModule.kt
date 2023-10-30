package com.example.network.di

import com.example.network.repository.ApiService
import com.example.network.repository.ApiServiceImpl
import com.example.network.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient():HttpClient{
        return HttpClient(Android){
            install(Logging){
                level=LogLevel.ALL
            }
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys=true
                })
            }
            install(DefaultRequest){
                url(Util.BASE_URL)
                header("app-id",Util.APP_ID)
            }
        }
    }

    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient):ApiService=ApiServiceImpl(httpClient)
}