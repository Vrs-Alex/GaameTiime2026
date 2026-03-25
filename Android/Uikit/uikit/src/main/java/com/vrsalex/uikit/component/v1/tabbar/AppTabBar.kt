package com.vrsalex.uikit.component.v1.tabbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun <T> AppTabBar(

    modifier: Modifier = Modifier
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
    ){
        AppTabBarBackground(modifier)


    }


}


@Composable
private fun AppTabBarBackground(modifier: Modifier) {

    val path = remember { Path() }
    val color = getBaseGradient()
    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        val width = size.width
        val height = size.height

        val tabHeight = 81.dp.toPx()
        val startHeight = height - tabHeight

        val blobWidth = 153.dp.toPx()
        val blobHeight = 28.dp.toPx()

        path.reset()
        path.moveTo(0f, height)
        path.lineTo(0f, startHeight)
        path.lineTo(center.x - blobWidth / 2, startHeight)
        path.cubicTo(
            x1 = center.x - blobWidth / 4, y1 = startHeight,
            x2 = center.x - blobWidth / 6, y2 = 0f,
            x3 = center.x, y3 = 0f
        )
        path.cubicTo(
            x1 = center.x + blobWidth / 6, y1 = 0f,
            x2 = center.x + blobWidth / 4 , y2 = startHeight,
            x3 = center.x + blobWidth / 2, y3 = startHeight
        )
        path.lineTo(width, startHeight)
        path.lineTo(width, height)
        path.close()

        drawPath(path, color)
    }

}