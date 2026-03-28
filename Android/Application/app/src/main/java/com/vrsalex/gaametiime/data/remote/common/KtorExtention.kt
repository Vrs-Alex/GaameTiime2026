package com.vrsalex.gaametiime.data.remote.common


import com.vrsalex.gaametiime.data.remote.dto.ServerError
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.utils.io.CancellationException
import java.io.IOException

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */


suspend inline fun <reified T>safeApiCall(block: suspend () -> HttpResponse): NetworkResult<T>{
    return try {
        val response = block()
        if (response.status.isSuccess()){
            val body = response.body<T>()
            NetworkResult.Success(body)
        } else {
            val errorMessage = try { response.body<ServerError>().message ?: "Ошибка" }
            catch (e: Exception) {
                if (e is CancellationException) throw e
                response.status.description
            }
            NetworkResult.Error.HttpError(response.status.value, errorMessage)
        }
    } catch (e: IOException){
        NetworkResult.Error.NoInternet
    } catch (e: Exception){
        if (e is CancellationException) throw e
        NetworkResult.Error.UnknownError(e.message.toString())
    }
}