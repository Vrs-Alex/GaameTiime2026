package com.vrsalex.gaametiime.presentation.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vrsalex.gaametiime.R
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun SplashScreen() {

    Box(
        Modifier.fillMaxSize()
            .background(getBaseGradient())
    ){

        Image(
            modifier = Modifier
                .size(width = 173.dp, height = 100.dp)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.logo),
            contentDescription = null
        )


        Image(
            modifier = Modifier.fillMaxWidth()
                .height(285.dp)
                .offset(y = (40).dp)
                .align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.splash),
            contentDescription = null
        )

    }

}