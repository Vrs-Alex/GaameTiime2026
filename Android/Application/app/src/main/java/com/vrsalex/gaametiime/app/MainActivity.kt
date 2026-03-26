package com.vrsalex.gaametiime.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vrsalex.gaametiime.presentation.navigation.GaameTiimeApp
import com.vrsalex.uikit.theme.GaameTiimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false
        setContent {
            GaameTiimeTheme {
                GaameTiimeApp()
            }
        }
    }
}

