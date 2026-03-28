package com.vrsalex.gaametiime.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Serializable
data class AuthDto(
    val accessToken: String
)
