package com.vrsalex.gaametiime.presentation.feature.landing

/**
 * Назначение:
 * Дата: 26.03.2026
 * Автор: Вартанян Алексей
 */
object LandingContract {

    data class State(
        val name: String,
        val items: List<CardItem>
    )


    sealed interface Effect {
        data class NavigateTo(val payload: Any): Effect
    }

    data class CardItem(
        val title: String,
        val description: String,
        val imageId: Int,
        val navTo: Any
    )

}