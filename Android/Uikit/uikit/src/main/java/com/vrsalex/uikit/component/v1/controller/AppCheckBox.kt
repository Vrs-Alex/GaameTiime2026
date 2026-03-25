package com.vrsalex.uikit.component.v1.controller

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppCheckBox(
    active: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(14.dp)
            .clip(RoundedCornerShape(2.dp))
            .border(
                width = 1.dp,
                color = AppTheme.colors.redText
            )
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = null
            ),
        contentAlignment = Alignment.Center
    ){
        if (active){
            Icon(
                painter = painterResource(R.drawable.checkmark),
                tint = null,
                contentDescription = null
            )
        }
    }

}