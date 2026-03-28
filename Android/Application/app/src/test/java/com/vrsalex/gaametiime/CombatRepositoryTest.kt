package com.vrsalex.gaametiime



/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
//class CombatRepositoryTest {
//
//    @org.junit.jupiter.api.Test
//    fun `getCombats should parse graphql response correctly`() = runTest {
//        // GIVEN: Создаем и запускаем мок-сервер
//        val mockServer = MockServer()
//        val apolloClient = ApolloClient.Builder()
//            .serverUrl(mockServer.url())
//            .build()
//        val repository = CombatRepository(apolloClient)
//
//        // Подготавливаем "сырой" ответ от сервера
//        val jsonResponse = """
//            {
//              "data": {
//                "combats": [
//                  { "id": "1", "gameName": "Halo 5", "winningPrice": "$4,000" }
//                ]
//              }
//            }
//        """.trimIndent()
//
//        mockServer.enqueue(MockResponse.Builder().body(jsonResponse).build())
//
//        // WHEN: Запрашиваем данные
//        val combats = repository.getCombats()
//
//        // THEN: Проверяем парсинг
//        assertThat(combats).hasSize(1)
//        assertThat(combats.first().gameName).isEqualTo("Halo 5")
//
//        mockServer.stop()
//    }
//}