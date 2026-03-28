package com.vrsalex.gaametiime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.v1.AppButton
import com.vrsalex.uikit.component.v1.AppCard
import com.vrsalex.uikit.component.v1.AppCheckbox
import com.vrsalex.uikit.component.v1.AppDateSelect
import com.vrsalex.uikit.component.v1.AppPagination
import com.vrsalex.uikit.component.v1.AppTabBar
import com.vrsalex.uikit.component.v1.AppTabBarItem
import com.vrsalex.uikit.component.v1.AppTertiaryButton
import com.vrsalex.uikit.component.v1.AppTextInput
import com.vrsalex.uikit.component.v1.AppTimer

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */


val leftItems = listOf(
    AppTabBarItem(
        payload = "1",
        title = "Statistics",
        iconId = R.drawable.statictic
    ),
    AppTabBarItem(
        payload = "discover",
        title = "Discover",
        iconId = R.drawable.location
    )
)

val rightItems = listOf(
    AppTabBarItem(
        payload = "3",
        title = "Chat",
        iconId = R.drawable.chat
    ),
    AppTabBarItem(
        payload = "Profile",
        title = "Profile",
        iconId = R.drawable.profile
    )
)

@Composable
fun Theme1(){
    Column(Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState())
        .safeDrawingPadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AppButton(
            text = "Let’s Combat!",
            { },
            modifier = Modifier.width(150.dp)
        )

        AppTertiaryButton(
            "Logout",
            R.drawable.logout,
            { }
        )


        var text by remember { mutableStateOf("") }
        AppTextInput(text, { text = it }, "Full Name")


        AppTimer(
            "00:00"
        )

        AppPagination(rememberPagerState(0) { 3 })

        var checked by remember { mutableStateOf(false) }
        AppCheckbox(checked, { checked = !checked })


        AppCard(
            title = "Schedule",
            description = "Easily schedule event/games then find like minded players for battle. You up for it?",
            imageId = R.drawable.image1,
            onClick = {}
        )


        AppDateSelect(
            onSelectDate = {},
            header = "FROM"
        )

        AppTabBar(
            leftItems = leftItems,
            rightItems = rightItems,
            centerItem = AppTabBarItem(
                payload = "center",
                iconId = R.drawable.schedule,
                title = ""
            ),
            onSelected = {},
            isSelected = { true },
            withNavBar = false
        )
    }
}