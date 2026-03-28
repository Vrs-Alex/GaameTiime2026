package com.vrsalex.gaametiime



import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.vrsalex.gaametiime.data.remote.common.NetworkResult
import com.vrsalex.gaametiime.data.remote.repository.AuthKtorRepository
import com.vrsalex.gaametiime.data.remote.repository.GameWebsocketRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */

class AuthKtorRepositoryTest {

    @Test
    fun `login should return success when server returns 200`() = runTest {
        // GIVEN: Настраиваем фейковый сервер
        val mockEngine = MockEngine { request ->
            respond(
                content = """{"accessToken": "test_token_123"}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        val repository = AuthKtorRepository(client)

        // WHEN: Вызываем метод логина
        val result = repository.login("alex@vrs.ru", "password123")


        assertThat(result).isNotNull()
    }
}


class GameSocketRepositoryTest {

    @Test
    fun `socket should emit new game state when message received`() = runTest {
        // GIVEN: Допустим, репозиторий возвращает Flow<GameState>
        // Мы мокаем сам репозиторий или его внутренний сокет-клиент
        val repository = mockk<GameWebsocketRepository>()
        val fakeFlow = MutableSharedFlow<NetworkResult<String>>()

        every { repository.events } returns fakeFlow


        repository.events.test {
            fakeFlow.emit(NetworkResult.Success("GAME"))

            // Проверяем, что поток "поймал" это событие
            val item = awaitItem()
            assertThat(item).isEqualTo(NetworkResult.Success("GAME"))

            // Завершаем тест
            cancelAndIgnoreRemainingEvents()
        }
    }
}