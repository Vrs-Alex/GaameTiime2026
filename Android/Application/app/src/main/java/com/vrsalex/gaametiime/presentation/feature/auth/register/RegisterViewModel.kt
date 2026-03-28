package com.vrsalex.gaametiime.presentation.feature.auth.register

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
class RegisterViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(RegisterContract.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<RegisterContract.Effect>()
    val effect = _effect.receiveAsFlow()


    fun updateFields(onUpdate: RegisterContract.State.() -> RegisterContract.State) {
        _state.update { it.onUpdate() }
    }

    fun onNext() = viewModelScope.launch {
        _effect.send(RegisterContract.Effect.Create)
    }

}