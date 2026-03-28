package com.vrsalex.gaametiime.presentation.feature.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.gaametiime.R
import com.vrsalex.uikit.theme.AppTheme
import kotlin.time.Duration.Companion.seconds

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun BlurScreen(
    title: String,
    buttonContent: @Composable () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null
) {

    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xB2FA5075))
            .clickable(
                enabled = false,
                interactionSource = null,
                indication = null,
                onClick = {},
            )
    ){

        Icon(
            painter = painterResource(R.drawable.close_icon),
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 44.dp, top = 68.dp)
                .size(30.dp)
                .clip(CircleShape)
                .clickable(
                    onClick = onBack
                ).padding(2.dp)
        )

        Column(
            Modifier.align(Alignment.TopCenter)
                .padding(top = 153.dp)
                .padding(horizontal = 81.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.success),
                contentDescription = null,
                modifier = Modifier.size(138.dp)
            )
            Spacer(Modifier.height(37.dp))

            Text(
                text = title,
                style = AppTheme.types.text.copy(
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(21.dp))

            Text(
                text = description?: "",
                style = AppTheme.types.text.copy(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(33.dp))

            buttonContent()
        }

    }

}