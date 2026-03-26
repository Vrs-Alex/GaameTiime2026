package com.vrsalex.gaametiime.data.onboarding

import com.vrsalex.gaametiime.data.local.DataStoreManager
import com.vrsalex.gaametiime.domain.onboarding.OnboardingRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Singleton
class OnboardingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
): OnboardingRepository {

    override suspend fun markAsCompleted(){
        dataStoreManager.markFirstStart()
    }

    override suspend fun isFirstLaunch() = dataStoreManager.getIsFirstStart().first()

}