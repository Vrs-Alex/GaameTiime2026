package com.vrsalex.gaametiime.presentation.feature.landing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Назначение:
 * Дата: 26.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun LandingScreen(
    viewModel: LandingViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle()


}