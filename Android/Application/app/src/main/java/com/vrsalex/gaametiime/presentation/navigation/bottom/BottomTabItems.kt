package com.vrsalex.gaametiime.presentation.navigation.bottom

import com.vrsalex.gaametiime.presentation.navigation.BottomTabDestination
import com.vrsalex.gaametiime.presentation.navigation.ChatDestination
import com.vrsalex.gaametiime.presentation.navigation.DiscoverDestination
import com.vrsalex.gaametiime.presentation.navigation.ProfileDestination
import com.vrsalex.gaametiime.presentation.navigation.ScheduleDestination
import com.vrsalex.gaametiime.presentation.navigation.StatisticDestination
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.v1.tabbar.AppTabItem

/**
 * Назначение:
 * Дата: 26.03.2026
 * Автор: Вартанян Алексей
 */


val bottomTabItemsLeft = listOf<AppTabItem<BottomTabDestination>>(
    AppTabItem(
        payload = StatisticDestination,
        title = "Statistics",
        iconId = R.drawable.statictic
    ),
    AppTabItem(
        payload = DiscoverDestination,
        title = "Discover",
        iconId = R.drawable.location
    )
)

val bottomTabCenterItem = AppTabItem<BottomTabDestination>(
    payload = ScheduleDestination,
    title = "",
    iconId = R.drawable.schedule
)

val bottomTabItemsRight = listOf<AppTabItem<BottomTabDestination>>(
    AppTabItem(
        payload = ChatDestination,
        title = "Chat",
        iconId = R.drawable.chat
    ),
    AppTabItem(
        payload = ProfileDestination,
        title = "Profile",
        iconId = R.drawable.profile
    )
)