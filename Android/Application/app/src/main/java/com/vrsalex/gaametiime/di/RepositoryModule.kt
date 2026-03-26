package com.vrsalex.gaametiime.di

import com.vrsalex.gaametiime.data.onboarding.OnboardingRepositoryImpl
import com.vrsalex.gaametiime.domain.onboarding.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindOnboardingRepository(impl: OnboardingRepositoryImpl): OnboardingRepository

}