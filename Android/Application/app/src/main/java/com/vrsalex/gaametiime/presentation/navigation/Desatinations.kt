package com.vrsalex.gaametiime.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */


@Serializable
data object OnboardingDestination


@Serializable
data object LoginDestination


@Serializable
data object RegisterDestination


@Serializable
data object LandingDestination


interface BottomTabDestination

@Serializable
data object StatisticDestination: BottomTabDestination

@Serializable
data object DiscoverDestination: BottomTabDestination

@Serializable
data object ScheduleDestination: BottomTabDestination

@Serializable
data object ChatDestination: BottomTabDestination

@Serializable
data object ProfileDestination: BottomTabDestination