package com.vrsalex.gaametiime.data.remote.repository

import androidx.compose.runtime.State
import com.apollographql.apollo.ApolloClient
import com.vrsalex.gaametiime.data.remote.dto.Statistic
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Singleton
class GithubApolloRepository @Inject constructor(
    private val apolloClient: ApolloClient
) {

//    suspend fun get(): Statistic {
//        apolloClient.query().execute()
//    }

}