package com.vrsalex.gaametiime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vrsalex.gaametiime.presentation.feature.landing.LandingScreen
import com.vrsalex.gaametiime.presentation.feature.onboarding.OnboardingScreen
import com.vrsalex.uikit.component.v1.button.AppButton

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppNavHost(navController: NavHostController, startDestination: Any?) {


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
            AppButton(
                "Next",
                {
                    navController.navigate(LandingDestination){
                        popUpTo(RegisterDestination){
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable<LandingDestination> {
            LandingScreen()
        }

    }


}