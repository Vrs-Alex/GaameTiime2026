package com.vrsalex.gaametiime.presentation.feature.auth.register

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
object RegisterContract {


    data class State(
        val fullName: String = "",
        val username: String = "",
        val phonePrefix: String = "",
        val phone: String = "",
        val email: String = "",
        val password: String = "",
        val passwordConfirm: String = ""
    )


    sealed interface Effect {
        data object Create: Effect
        data object OnLogin: Effect
    }

}