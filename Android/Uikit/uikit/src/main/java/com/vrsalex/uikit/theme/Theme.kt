package com.vrsalex.uikit.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

@Immutable
data class AppColor(
    val gradient1: Color,
    val gradient2: Color,
    val redText: Color
)


@Immutable
data class AppType(
    val text: TextStyle
)


val LocalAppColors = staticCompositionLocalOf {
    AppColor(
        gradient1 = Color.Unspecified,
        gradient2 = Color.Unspecified,
        redText = Color.Unspecified
    )
}

val LocalAppTypes = staticCompositionLocalOf {
    AppType(
        text = TextStyle.Default
    )
}


@Composable
fun GaameTiimeTheme(
    content: @Composable () -> Unit
){

    val view = LocalView.current
    DisposableEffect(Unit) {
        val window = (view.context as? Activity)?.window
        window?.let {
            val controller = WindowCompat.getInsetsController(it, view)
            controller.isAppearanceLightStatusBars = true
            controller.isAppearanceLightNavigationBars = true
        }

        onDispose {  }
    }

    val colors = AppColor(
        gradient1 = Gradient1,
        gradient2 = Gradient2,
        redText = RedText
    )

    val types = AppType(
        text = TextStyle(
            fontFamily = poppinsFont,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    )

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypes provides types,
        content = content
    )

}


object AppTheme {

    val colors : AppColor
        @Composable
        get() = LocalAppColors.current

    val types : AppType
        @Composable
        get() = LocalAppTypes.current

}