package com.vrsalex.gaametiime.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vrsalex.gaametiime.presentation.feature.splash.SplashScreen
import com.vrsalex.gaametiime.presentation.navigation.bottom.bottomTabCenterItem
import com.vrsalex.gaametiime.presentation.navigation.bottom.bottomTabItemsLeft
import com.vrsalex.gaametiime.presentation.navigation.bottom.bottomTabItemsRight
import com.vrsalex.uikit.component.v1.tabbar.AppTabBar

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun GaameTiimeApp(
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value

    val isShowBottomTab = remember(currentDestination) {
        currentDestination?.destination?.hierarchy?.any {
            it.hasRoute(LandingDestination::class) || it.hasRoute(DiscoverDestination::class) } == true
    }

    if (startDestination == null) SplashScreen()
    else {
        Scaffold(
            containerColor = Color.White,
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (isShowBottomTab) {
                    AppTabBar(
                        leftItems = bottomTabItemsLeft,
                        rightItems = bottomTabItemsRight,
                        centerItem = bottomTabCenterItem,
                        onItemClick = {
                            navController.navigate(it)
                        },
                    )
                }
            }
        ) { paddingValues ->
            AppNavHost(navController, startDestination)
        }
    }
}