package com.vrsalex.gaametiime.domain.onboarding

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
interface OnboardingRepository {

    suspend fun markAsCompleted()

    suspend fun isFirstLaunch(): Boolean

}