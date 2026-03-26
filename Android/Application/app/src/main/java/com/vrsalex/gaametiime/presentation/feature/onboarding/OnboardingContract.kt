package com.vrsalex.gaametiime.presentation.feature.onboarding

import androidx.compose.ui.unit.Dp

/**
 * Назначение:
 * Дата: 26.03.2026
 * Автор: Вартанян Алексей
 */
object OnboardingContract {

    data class State(
        val imageId: Int,
        val title: String,
        val description: String,
        val showSkip: Boolean = true,
        val mainButtonText: String? = null
    )

}