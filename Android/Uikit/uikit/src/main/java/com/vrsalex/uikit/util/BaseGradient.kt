package com.vrsalex.uikit.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Theme2Base

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


fun getBase2Gradient() = Brush.verticalGradient(
    listOf(Color(0xFFEB0DFE), Color(0xFF11CADE))
)


fun getBase2_1Gradient() = Brush.linearGradient(
    0.0499f to Theme2Base,
    0.7567f to Color(0x40FFFFFF),
    start = Offset.Infinite,
    end = Offset.Zero
)