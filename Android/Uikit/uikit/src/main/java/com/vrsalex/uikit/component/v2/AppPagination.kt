package com.vrsalex.uikit.component.v2

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Theme2Base

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppPagination(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        repeat(pagerState.pageCount){ page ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .border(
                        width = 0.2.dp,
                        shape = CircleShape,
                        brush = Brush.linearGradient(
                            0.0499f to Theme2Base,
                            0.7567f to Color(0x40FFFFFF),
                            start = Offset.Infinite,
                            end = Offset.Zero
                        )
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(60.dp),
                        shadow = Shadow(
                            radius = 10.dp,
                            spread = 0.dp,
                            offset = DpOffset(2.dp, 2.dp),
                            color = Color(0x4D8A9BBD)
                        )
                    ).innerShadow(
                        shape = RoundedCornerShape(60.dp),
                        shadow = Shadow(
                            radius = 6.dp,
                            spread = 0.dp,
                            offset = DpOffset((-2).dp, (-2).dp),
                            color = Color(0xB2FFFFFF)
                        )
                    )
            ){
                if (pagerState.currentPage == page){
                    Box(
                        Modifier.size(6.dp)
                            .dropShadow(
                                shape = RoundedCornerShape(60.dp),
                                shadow = Shadow(
                                    radius = 10.dp,
                                    spread = 0.dp,
                                    offset = DpOffset(2.dp, 2.dp),
                                    color = Color(0x4D8A9BBD)
                                )
                            ).dropShadow(
                                shape = RoundedCornerShape(60.dp),
                                shadow = Shadow(
                                    radius = 6.dp,
                                    spread = 0.dp,
                                    offset = DpOffset((-2).dp, (-2).dp),
                                    color = Color(0xB2FFFFFF)
                                )
                            )
                            .clip(CircleShape)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFFEB0DFE), Color(0xFF11CADE))
                                )
                            )
                    )
                }
            }
        }
    }

}