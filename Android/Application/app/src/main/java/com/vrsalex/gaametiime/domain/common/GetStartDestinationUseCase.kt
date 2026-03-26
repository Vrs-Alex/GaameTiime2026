package com.vrsalex.gaametiime.domain.common

import com.vrsalex.gaametiime.domain.onboarding.OnboardingRepository
import com.vrsalex.gaametiime.presentation.navigation.LoginDestination
import com.vrsalex.gaametiime.presentation.navigation.OnboardingDestination
import com.vrsalex.gaametiime.presentation.navigation.RegisterDestination
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

@Singleton
class GetStartDestinationUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke(): Any {
        return when {
            onboardingRepository.isFirstLaunch() -> OnboardingDestination
            else -> RegisterDestination
        }
    }

}