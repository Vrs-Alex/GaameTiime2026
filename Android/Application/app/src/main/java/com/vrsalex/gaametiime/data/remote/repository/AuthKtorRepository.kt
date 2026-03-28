package com.vrsalex.gaametiime.data.remote.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Singleton
class AuthKtorRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun login(email: String, password: String) {
        val response = client.post("auth/login")
    }

}