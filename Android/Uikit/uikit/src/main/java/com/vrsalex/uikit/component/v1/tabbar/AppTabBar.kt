package com.vrsalex.uikit.component.v1.tabbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun <T> AppTabBar(
    leftItems: List<AppTabItem<T>>,
    rightItems: List<AppTabItem<T>>,
    centerItem: AppTabItem<T>,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        AppTabBarBackground(modifier)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 29.dp),
        ) {
            leftItems.forEach { item ->
                TabIconElement(item, Modifier.weight(1f), onItemClick)
            }

            Spacer(modifier = Modifier.width(90.dp))


            rightItems.forEach { item ->
                TabIconElement(item, Modifier.weight(1f), onItemClick)
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 15.dp)
        ) {
            CentralIconElement(centerItem, onItemClick)
        }
    }
}

@Composable
fun <T> CentralIconElement(item: AppTabItem<T>, onClick: (T) -> Unit) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .border(
                width = 5.dp,
                color = Color.White,
                shape = CircleShape
            )
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = { onClick(item.payload) }
            ),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(item.iconId),
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.size(29.dp),

        )
    }
}


@Composable
private fun <T> TabIconElement(item: AppTabItem<T>, modifier: Modifier, onClick: (T) -> Unit) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = { onClick(item.payload) }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(item.iconId),
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = item.title,
            style = AppTheme.types.text,
            fontSize = 10.sp,
            color = Color.White
        )
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