package com.vrsalex.uikit.component.v1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppTertiaryButton(
    text: String,
    iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.height(24.dp).clickable(
            onClick = onClick,
            indication = ripple(),
            interactionSource = null
        )
    ) {

        Icon(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = null
        )

        Text(
            text = text,
            style = AppTheme.types.text.copy(
                fontSize = 16.sp,
                color = Color.Black
            )
        )

    }

}