package com.vrsalex.gaametiime.presentation.feature.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vrsalex.gaametiime.presentation.navigation.ChatDestination
import com.vrsalex.gaametiime.presentation.navigation.DiscoverDestination
import com.vrsalex.gaametiime.presentation.navigation.ScheduleDestination
import com.vrsalex.gaametiime.presentation.navigation.StatisticDestination
import com.vrsalex.uikit.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Назначение:
 * Дата: 26.03.2026
 * Автор: Вартанян Алексей
 */
@HiltViewModel
class LandingViewModel @Inject constructor(): ViewModel() {


    private val _state = MutableStateFlow(
        LandingContract.State(
            name = "Stone Stellar",
            items = listOf(
                LandingContract.CardItem(
                    title = "Schedule",
                    description = "Easily schedule event/games then find like minded players for battle. You up for it?",
                    imageId = R.drawable.image1,
                    navTo = ScheduleDestination
                ),
                LandingContract.CardItem(
                    title = "Statistics",
                    description = "All data from previous and upcoming games can be found here ",
                    imageId = R.drawable.image4,
                    navTo = StatisticDestination
                ),
                LandingContract.CardItem(
                    title = "Discover  Combats",
                    description = "Find out what’s new and compete among players with new challenges and earn cash with game points",
                    imageId = R.drawable.image2,
                    navTo = DiscoverDestination
                ),
                LandingContract.CardItem(
                    title = "Message Players",
                    description = "Found the profile of a player that interests you? Start a conversation",
                    imageId = R.drawable.image3,
                    navTo = ChatDestination
                )
            )
        )
    )
    val state = _state.asStateFlow()


    private val _channel = Channel<LandingContract.Effect>()
    val channel = _channel.receiveAsFlow()

    fun navTo(payload: Any) = viewModelScope.launch {
        _channel.send(LandingContract.Effect.NavigateTo(payload))
    }


}