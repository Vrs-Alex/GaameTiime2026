package com.vrsalex.uikit.component.v2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Theme2Back
import com.vrsalex.uikit.theme.Theme2Base
import com.vrsalex.uikit.theme.Theme2Color2

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppButton(
    text: String,
    iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .width(200.dp)
            .height(30.dp)
            .dropShadow(
                shape = RoundedCornerShape(60.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 0.dp,
                    offset = DpOffset(3.dp, 3.dp),
                    color = Color(0x7A8A9BBD)
                )
            ).dropShadow(
                shape = RoundedCornerShape(60.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 0.dp,
                    offset = DpOffset((-3).dp, (-3).dp),
                    color = Color(0xB2FFFFFF)
                )
            )
            .clip(RoundedCornerShape(60.dp))
            .background(Theme2Base)
            .background(Brush.linearGradient(
                0.0449f to Color(0x4DFFFFFF),
                0.9545f to Color(0x4DE9F0F7),
                start = Offset.Infinite,
                end = Offset.Zero
            )).border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    0.0459f to Color(0xFFD6E5F2),
                    0.7418f to Color(0x4DFFFFFF),
                    start = Offset.Infinite,
                    end = Offset.Zero
                ),
                shape = RoundedCornerShape(60.dp)
            ).clickable(
                indication = ripple(),
                interactionSource = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ){

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = null,
                tint = null,
                modifier = Modifier.size(10.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = text,
                style = AppTheme.types.text.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Theme2Color2
                )
            )
        }

    }

}