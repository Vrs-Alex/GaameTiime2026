package com.vrsalex.gaametiime.data.remote.repository

import com.vrsalex.gaametiime.data.remote.common.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Singleton
class GameWebsocketRepository @Inject constructor(
    private val client: HttpClient
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _events = MutableSharedFlow<NetworkResult<Any>>(replay = 1)
    val events = _events.asSharedFlow()

    private val sendChannel = Channel<Any>(Channel.BUFFERED)

    fun connect() {
        scope.launch {
            var delayMs = 1000L
            val maxDelayMs = 30000L

            while (isActive) {
                try {
                    client.webSocket(urlString = "ws://123.123/realtime") {
                        delayMs = 1000L

                        val sendJob = launch {
                            sendChannel.receiveAsFlow()
                                .onEach { action -> println("Sending: $action") } // Можно добавить логи
                                .collect { action ->
                                    sendSerialized(action)
                                }
                        }

                        while (isActive) {
                            try {
                                val event = receiveDeserialized<Any>()
                                _events.emit(NetworkResult.Success(event))
                            } catch (e: Exception) {
                                if (e is CancellationException) throw e
                                _events.emit(NetworkResult.Error.UnknownError(e.message ?: "Decode error"))
                            }
                        }

                        sendJob.cancel() // Отменяем отправку при закрытии сокета
                    }
                } catch (e: Exception) {
                    if (e is CancellationException) throw e
                    _events.emit(NetworkResult.Error.UnknownError(e.message.toString()))
                } finally {
                    // 3. ЭКСПОНЕНЦИАЛЬНАЯ ЗАДЕРЖКА ПРИ РЕКОННЕКТЕ
                    delay(delayMs)
                    delayMs = (delayMs * 2).coerceAtMost(maxDelayMs)
                }
            }
        }
    }

    fun send(action: Any) {
        scope.launch {
            sendChannel.send(action)
        }
    }
}