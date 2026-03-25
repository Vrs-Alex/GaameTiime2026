package com.vrsalex.uikit.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.AppType

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

@Composable
fun getBaseGradient() = Brush.linearGradient(
    listOf(AppTheme.colors.gradient1, AppTheme.colors.gradient2),
    start = Offset.Zero,
    end = Offset.Infinite
)