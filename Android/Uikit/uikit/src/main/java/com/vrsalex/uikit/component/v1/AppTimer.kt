package com.vrsalex.uikit.component.v1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 27.03.2026
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
            .background(getBaseGradient())
            .padding(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.timer),
            style = AppTheme.types.text.copy(
                fontSize = 12.sp,
                color = Color.White
            )
        )
        Spacer(Modifier.height(14.dp))
        Text(
            text = time,
            style = AppTheme.types.text.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }

}