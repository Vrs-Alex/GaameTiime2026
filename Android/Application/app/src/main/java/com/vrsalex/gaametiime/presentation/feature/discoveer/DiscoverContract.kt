package com.vrsalex.gaametiime.presentation.feature.discoveer

import com.vrsalex.gaametiime.domain.combat.Combat
import com.vrsalex.gaametiime.domain.player.Player

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
object DiscoverContract {

    data class State(
        val searchQuery: String = "",
        val trendingCombats: List<Combat> = emptyList(),
        val popularPlayers: List<Player> = emptyList(),
        val latestCombats: List<Combat> = emptyList(),
        val errorMessage: String? = null
    )

    sealed interface Effect {
        data object OnBack: Effect
        data class OnOpenPlayer(val id: Int): Effect
        data class OnOpenCombat(val id: String): Effect
    }

}