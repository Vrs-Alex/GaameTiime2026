package com.vrsalex.uikit.component.v1.card

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppTimer(
    time: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .height(100.dp)
            .width(295.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(getBaseGradient()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(7.dp))
        Text(
            text = "Timer",
            style = AppTheme.types.text,
            color = Color.White,
            fontSize = 12.sp
        )
        Spacer(Modifier.height(14.dp))
        Text(
            text = time,
            style = AppTheme.types.text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 30.sp
        )
    }

}