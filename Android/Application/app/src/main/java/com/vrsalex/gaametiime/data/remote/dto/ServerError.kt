package com.vrsalex.gaametiime.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Serializable
data class ServerError(
    val message: String,
    val code: Int
)
