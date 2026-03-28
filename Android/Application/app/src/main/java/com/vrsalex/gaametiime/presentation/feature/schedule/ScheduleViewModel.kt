package com.vrsalex.gaametiime.presentation.feature.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
@HiltViewModel
class ScheduleViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(ScheduleContract.State())
    val sate  =_state.asStateFlow()


    private val _channel = Channel<ScheduleContract.Effect>()
    val channel = _channel.receiveAsFlow()


    fun sendEffect(effect: ScheduleContract.Effect) = viewModelScope.launch {
        _channel.send(effect)
    }

    fun updateData(update: ScheduleContract.State.() -> ScheduleContract.State) = viewModelScope.launch {
        _state.update { it.update() }
    }
}