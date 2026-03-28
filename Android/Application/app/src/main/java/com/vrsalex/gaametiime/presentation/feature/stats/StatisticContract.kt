package com.vrsalex.gaametiime.presentation.feature.stats

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
object StatisticContract {

    data class State(
        val totalRepos: Int,
        val publicPercent: Float,
        val weekCommits: List<Int>,
        val totalCommits: Int
    )

}