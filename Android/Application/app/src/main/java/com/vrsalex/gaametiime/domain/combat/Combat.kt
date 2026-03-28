package com.vrsalex.gaametiime.domain.combat

import com.vrsalex.gaametiime.domain.player.Player

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
data class Combat(
    val id: String,
    val gameName: String,
    val status: CombatStatus,
    val winningPrice: String,
    val host: Player,
    val guests: List<Player>,
    val description: String? = null,
    val category: String? = null,
    val startTime: Long? = null,
    val endTime: Long? = null,
    val prizePool: List<String> = emptyList()
)
