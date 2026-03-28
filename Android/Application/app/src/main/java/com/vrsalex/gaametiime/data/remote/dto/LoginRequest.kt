package com.vrsalex.gaametiime.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)
