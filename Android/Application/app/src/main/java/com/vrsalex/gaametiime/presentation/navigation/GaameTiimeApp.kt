package com.vrsalex.gaametiime.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vrsalex.gaametiime.presentation.feature.landing.drawer.DrawerScreen
import com.vrsalex.gaametiime.presentation.feature.splash.SplashScreen
import com.vrsalex.gaametiime.presentation.navigation.bottom.bottomTabCenterItem
import com.vrsalex.gaametiime.presentation.navigation.bottom.bottomTabItemsLeft
import com.vrsalex.gaametiime.presentation.navigation.bottom.bottomTabItemsRight
import com.vrsalex.uikit.component.v1.AppTabBar
import kotlinx.coroutines.launch

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

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(windowInsets = WindowInsets(0.dp)) {
                DrawerScreen(onClose = { scope.launch { drawerState.close() } })
            }
        },
        gesturesEnabled = drawerState.currentValue != DrawerValue.Closed
    ) {
        if (startDestination == null) SplashScreen()
        else {
            Scaffold(
                containerColor = Color.White,
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    AnimatedVisibility(
                        isShowBottomTab,
                        enter = slideInVertically { it },
                        exit = slideOutVertically { it }
                    ) {
                        AppTabBar(
                            leftItems = bottomTabItemsLeft,
                            rightItems = bottomTabItemsRight,
                            centerItem = bottomTabCenterItem,
                            onSelected = {
                                navController.navigate(it) {
                                    popUpTo(LandingDestination) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                            isSelected = { false }
                        )
                    }
                }
            ) { paddingValues ->
                AppNavHost(navController, startDestination, { scope.launch { drawerState.open() }} )
            }
        }
    }
}