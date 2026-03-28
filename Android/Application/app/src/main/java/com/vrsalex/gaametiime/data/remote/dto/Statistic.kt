package com.vrsalex.gaametiime.data.remote.dto

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
data class Statistic(
    val totalRepositories: Int,
    val weekCommits: List<Int>
)
