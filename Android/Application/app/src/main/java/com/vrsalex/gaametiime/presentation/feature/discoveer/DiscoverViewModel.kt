package com.vrsalex.gaametiime.presentation.feature.discoveer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.gaametiime.domain.combat.Combat
import com.vrsalex.gaametiime.domain.combat.CombatStatus
import com.vrsalex.gaametiime.domain.player.Player
import com.vrsalex.gaametiime.domain.player.PlayerStatus
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
class DiscoverViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(DiscoverContract.State())
    val state = _state.asStateFlow()


    private val _channel = Channel<DiscoverContract.Effect>()
    val channel = _channel.receiveAsFlow()

    init {
        // 1. Создаем пул игроков
        val players = listOf(
            Player("1", "Scott Brown", null, "Gold Player", PlayerStatus.ONLINE),
            Player("2", "Stone Stella", null, "Silver Player", PlayerStatus.AWAY),
            Player("3", "Shedrac Bety", null, "Pro Player", PlayerStatus.ONLINE),
            Player("4", "Usman Terik", null, "Bronze Player", PlayerStatus.OFFLINE),
            Player("5", "Jully Paga", null, "Gold Player", PlayerStatus.ONLINE),
            Player("6", "Dada Awuri", null, "Silver Player", PlayerStatus.ONLINE),
            Player("7", "Oyibo Pepper", null, "Pro Player", PlayerStatus.AWAY),
            Player("8", "Shema Laset", null, "Gold Player", PlayerStatus.OFFLINE)
        )

        // 2. Создаем список боев
        val combats = listOf(
            Combat(
                id = "c1",
                gameName = "Halo 5",
                status = CombatStatus.OPEN,
                winningPrice = "$4,000",
                host = players[0],
                guests = listOf(players[1])
            ),
            Combat(
                id = "c2",
                gameName = "Mortal Kombat",
                status = CombatStatus.CANCELLED,
                winningPrice = "$7,000",
                host = players[2],
                guests = List(4) { players[3] } // Тут будет отображаться +3
            ),
            Combat(
                id = "c3",
                gameName = "NFS(Rivals 2)",
                status = CombatStatus.ACTIVE,
                winningPrice = "$4,000",
                host = players[4],
                guests = listOf(players[5])
            ),
            Combat(
                id = "c4",
                gameName = "God Hand",
                status = CombatStatus.ACTIVE,
                winningPrice = "$1,000",
                host = players[6],
                guests = List(12) { players[7] } // Тут будет отображаться +11
            )
        )

        // 3. Собираем итоговое состояние
        val discoverState = DiscoverContract.State(
            searchQuery = "",
            trendingCombats = combats.take(4),
            popularPlayers = players.shuffled().take(5),
            latestCombats = combats.reversed()
        )
        _state.update { discoverState }
    }


    fun sendEffect(effect: DiscoverContract.Effect) = viewModelScope.launch{
        _channel.send(effect)
    }


    fun updateSearch(newValue: String){
        _state.update { it.copy(searchQuery = newValue) }
    }
}