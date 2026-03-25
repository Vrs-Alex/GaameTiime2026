package com.vrsalex.uikit.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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


@Composable
fun getBaseGradient2() = Brush.linearGradient(
    0.049f to Color(0x4DFFFFFF),
    0.9545f to Color(0x4DE9F0F7),
    start = Offset.Infinite,
    end = Offset.Zero
)
