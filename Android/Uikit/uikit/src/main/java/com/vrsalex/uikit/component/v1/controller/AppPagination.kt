package com.vrsalex.uikit.component.v1.controller

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppPagination(
    state: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(state.pageCount){ page ->
            val isActive = remember(state.currentPage ) { page == state.currentPage }
            val color by animateColorAsState(
                if (isActive) AppTheme.colors.redText
                else AppTheme.colors.redText.copy(alpha = 0.3f)
            )
            Box(
                Modifier.size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}