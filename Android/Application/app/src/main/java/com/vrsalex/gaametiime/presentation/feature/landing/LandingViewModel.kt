package com.vrsalex.gaametiime.presentation.feature.landing

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Назначение:
 * Дата: 26.03.2026
 * Автор: Вартанян Алексей
 */
@HiltViewModel
class LandingViewModel @Inject constructor(): ViewModel() {


    private val _state = MutableStateFlow(LandingContract.State("Vrs Alex",""))
    val state = _state.asStateFlow()

}