package com.vrsalex.gaametiime.data.remote.common

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
sealed interface NetworkResult <out T>{
    data class Success<T>(val data: T): NetworkResult<T>
    sealed interface Error: NetworkResult<Nothing> {
        data object NoInternet: Error
        data class UnknownError(val message: String): Error
        data class HttpError(val code: Int, val message: String): Error
    }
}