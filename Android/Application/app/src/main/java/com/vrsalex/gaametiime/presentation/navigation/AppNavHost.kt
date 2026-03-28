package com.vrsalex.gaametiime.presentation.navigation

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalResources
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vrsalex.gaametiime.R
import com.vrsalex.gaametiime.presentation.feature.auth.register.RegisterScreen
import com.vrsalex.gaametiime.presentation.feature.discoveer.DiscoverScreen
import com.vrsalex.gaametiime.presentation.feature.game.CircleScreen
import com.vrsalex.gaametiime.presentation.feature.game.PuzzleScreen
import com.vrsalex.gaametiime.presentation.feature.landing.LandingScreen
import com.vrsalex.gaametiime.presentation.feature.onboarding.OnboardingScreen
import com.vrsalex.gaametiime.presentation.feature.schedule.ScheduleScreen
import com.vrsalex.gaametiime.presentation.feature.stats.GithubStatsScreen

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppNavHost(navController: NavHostController, startDestination: Any?, onDrawerOpen: () -> Unit) {


    NavHost(
        navController =  navController,
        startDestination = startDestination!!
    ){

        composable<OnboardingDestination> {
            OnboardingScreen(){
                navController.navigate(RegisterDestination){
                    popUpTo(OnboardingDestination){
                        inclusive = true
                    }
                }
            }
        }

        composable<LoginDestination> {

        }

        composable<RegisterDestination> {
            RegisterScreen(){
                navController.navigate(LandingDestination){
                    popUpTo(RegisterDestination){
                        inclusive = true
                    }
                }
            }
        }


        composable<LandingDestination> {
            LandingScreen(onDrawerOpen = { onDrawerOpen() }){
                navController.navigate(it){
                    popUpTo(LandingDestination){
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }
        }


        composable<StatisticDestination> {
            GithubStatsScreen()
        }

        composable<DiscoverDestination> {
            DiscoverScreen()
        }

        composable<ScheduleDestination> {
            ScheduleScreen(
                onBack = { navController.popBackStack() },
                onStat = {
                    navController.navigate(StatisticDestination){
                        popUpTo(LandingDestination)
                    }
                }
            )
        }

        composable<ChatDestination> {
            CircleScreen()
        }

        composable<ProfileDestination> {
            val image = BitmapFactory.decodeResource(LocalResources.current, R.drawable.images)
            PuzzleScreen(image)
        }

    }


}