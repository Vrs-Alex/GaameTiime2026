package com.vrsalex.gaametiime.data.remote.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.pingInterval
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        json: Json
    ): HttpClient = HttpClient(OkHttp){

        defaultRequest {
            url("http://192.168.0.181:8080/")
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation){
            json(json)
        }

        install(Logging){
            level = LogLevel.ALL
            logger = Logger.ANDROID
        }

        install(WebSockets){
            pingInterval = 15_000.seconds
            contentConverter = KotlinxWebsocketSerializationConverter(json)
        }

        install(Auth){
            bearer {
                loadTokens {
                    BearerTokens("TOKEN", null)
                }
                refreshTokens {
                    //
                    BearerTokens("TOKEN", null)
                }
            }
        }

        install(HttpRequestRetry){
            maxRetries = 3
            exponentialDelay()
        }

        install(HttpTimeout){
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 15_000
            requestTimeoutMillis = 30_000
        }
    }

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl("https://api.github.com/graphql")
        .okHttpClient(
            OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Authorization", "Bearer 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")
                    .build()
                chain.proceed(request)
            }.build()
        ).build()




}