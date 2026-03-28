package com.vrsalex.gaametiime.domain.player

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
data class Player(
    val id: String,
    val name: String,
    val avatarUrl: String?,
    val rank: String? = null,
    val status: PlayerStatus = PlayerStatus.OFFLINE
)

