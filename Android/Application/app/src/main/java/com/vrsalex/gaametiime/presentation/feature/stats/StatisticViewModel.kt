package com.vrsalex.gaametiime.presentation.feature.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.vrsalex.gaametiime.GetGithubStatsQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */


sealed interface GithubUiState {
   object Loading : GithubUiState
   data class Success(
      val totalRepos: Int,
      val publicPercent: Float,
      val weekCommits: List<Int>, // Для столбцов и линии
      val totalCommits: Int
   ) : GithubUiState
   data class Error(val message: String) : GithubUiState
}

@HiltViewModel
class StatisticViewModel @Inject constructor(
   private val apolloClient: ApolloClient
): ViewModel() {

   var uiState by mutableStateOf<GithubUiState>(GithubUiState.Loading)
      private set


   fun fetchStats(login: String) {
      viewModelScope.launch {
         uiState = GithubUiState.Loading
         try {
            val response = apolloClient.query(GetGithubStatsQuery(login)).execute()
            val user = response.data?.user

            if (user != null) {
               // 1. Считаем проценты для кольца
               val total = user.repositories.totalCount
               val public = user.repositories.nodes?.count { it?.isPrivate == false } ?: 0
               val percent = if (total > 0) (public.toFloat() / total * 100) else 0f

               // 2. Достаем коммиты за последнюю неделю
               val days = user.contributionsCollection.contributionCalendar.weeks.lastOrNull()
                  ?.contributionDays?.map { it.contributionCount } ?: listOf(0,0,0,0,0,0,0)

               uiState = GithubUiState.Success(
                  totalRepos = total,
                  publicPercent = percent,
                  weekCommits = days,
                  totalCommits = days.sum()
               )
            } else {
               uiState = GithubUiState.Error("User not found")
            }
         } catch (e: Exception) {
            uiState = GithubUiState.Error(e.message ?: "Unknown error")
         }
      }
   }

   init {
      fetchStats("Vrs-Alex")
   }


}