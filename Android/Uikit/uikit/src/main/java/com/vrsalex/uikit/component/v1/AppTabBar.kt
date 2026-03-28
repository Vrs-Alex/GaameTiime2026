package com.vrsalex.uikit.component.v1

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */

data class AppTabBarItem<T>(
    val payload: T,
    val title: String,
    val iconId: Int
)

@Composable
fun <T> AppTabBar(
    leftItems: List<AppTabBarItem<T>>,
    rightItems: List<AppTabBarItem<T>>,
    centerItem: AppTabBarItem<T>,
    onSelected: (T) -> Unit,
    isSelected: (T) -> Boolean,
    modifier: Modifier = Modifier,
    withNavBar: Boolean = true
) {

    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val fullTabHeight = 109.dp
    val totalHeight = if (withNavBar) fullTabHeight + navBarPadding
        else fullTabHeight

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(totalHeight)
    ){
        AppTabBarBackground(modifier.fillMaxSize(), fullTabHeight)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp).padding(horizontal = 10.dp),
        ) {
            leftItems.forEach {
                TabItem(
                    it,
                    modifier.weight(1f).clickable(onClick = { onSelected(it.payload) })
                )
            }

            Spacer(Modifier.width(75.dp))

            rightItems.forEach {
                TabItem(
                    it,
                    modifier.weight(1f).clickable(onClick = { onSelected(it.payload) })
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 15.dp)
                .size(56.dp)
                .clip(CircleShape)
                .border(5.dp, Color.White, CircleShape)
                .clickable(
                    onClick = { onSelected(centerItem.payload) }
                )
                .padding(14.dp),
            contentAlignment = Alignment.Center,
        ){
            Icon(
                painter = painterResource(centerItem.iconId),
                contentDescription = null,
                tint = null,
                modifier = Modifier.size(29.dp)
            )
        }
    }
}

@Composable
private fun <T> TabItem(item: AppTabBarItem<T>, modifier: Modifier){
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.5.dp)
    ) {
        Icon(
            painter = painterResource(item.iconId),
            contentDescription = null,
            tint = null,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = item.title,
            style = AppTheme.types.text.copy(
                fontSize = 10.sp,
                color = Color.White
            )
        )
    }
}

@Composable
private fun AppTabBarBackground(modifier: Modifier, fullTabHeight: Dp){

    val gradient = getBaseGradient()
    val path = remember { Path() }
    Canvas(modifier) {
        val tabHeight = 81.dp

        val centerX = size.width / 2

        val topHeight = (fullTabHeight - tabHeight).toPx()
        val bottomHeight = size.height

        val startX = 0f
        val endX = size.width

        val blobHeight = (fullTabHeight - tabHeight - 28.dp).toPx()
        val blobWidth = 153.dp.toPx()


        path.moveTo(startX, bottomHeight)
        path.lineTo(startX, topHeight)
        path.lineTo(centerX - blobWidth / 2, topHeight)
        path.cubicTo(
            x1 = centerX - blobWidth / 4, y1 = topHeight,
            x2 = centerX - blobWidth / 6, y2 = blobHeight,
            x3 = centerX, y3 = blobHeight
        )
        path.cubicTo(
            x1 = centerX + blobWidth / 6, y1 = blobHeight,
            x2 = centerX + blobWidth / 4, y2 = topHeight,
            x3 = centerX + blobWidth / 2, y3 = topHeight
        )
        path.lineTo(endX, topHeight)
        path.lineTo(endX, bottomHeight)

        path.close()


        drawPath(path, brush = gradient)
    }

}