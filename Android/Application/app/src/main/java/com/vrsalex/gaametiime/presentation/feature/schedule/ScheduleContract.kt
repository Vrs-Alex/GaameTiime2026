package com.vrsalex.gaametiime.presentation.feature.schedule

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
object ScheduleContract {

    data class State(
        val gameName: String = "",
        val category: String = "",
        val price: String = "",
        val description: String = "",
        val isNotify: Boolean = false,
        val isSuccess: Boolean = false
    )

    sealed interface Effect {
        data object Back: Effect
        data object OnStatistic: Effect
    }


}