package com.vrsalex.uikit.component.v1

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.theme.AppTheme

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppCheckbox(
    checked: Boolean,
    onChecked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(14.dp)
            .clip(RoundedCornerShape(1.5.dp))
            .border(1.dp, AppTheme.colors.redElemment)
            .clickable(
                interactionSource = null,
                indication = ripple(),
                onClick = onChecked
            )
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ){

        Crossfade(checked) {
            if (it){
                Icon(
                    painter = painterResource(R.drawable.checkmark),
                    tint = null,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}