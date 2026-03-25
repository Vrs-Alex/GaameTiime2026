package com.vrsalex.uikit.component.v2.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient2

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Int? = null
) {

    Box(
        modifier = modifier
            .width(200.dp)
            .height(30.dp)
            .dropShadow(
                shape = RoundedCornerShape(60.dp),
                shadow = Shadow(
                    offset = DpOffset((-3).dp, (-3).dp),
                    radius = 10.dp,
                    spread = 0.dp,
                    color = Color(0xB3FFFFFF)
                )
            ).dropShadow(
                shape = RoundedCornerShape(60.dp),
                shadow = Shadow(
                    offset = DpOffset(3.dp, 3.dp),
                    radius = 10.dp,
                    spread = 0.dp,
                    color = Color(0x7A8A9BBD)
                )
            )
            .background(Color(0xFFE9F0F7), RoundedCornerShape(60.dp))
            .background(getBaseGradient2(Color(0x12121212)), RoundedCornerShape(60.dp))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    0.0459f to Color(0xFFD6E5F2),
                    0.7418f to Color(0x4DE9F0F7),
                    start = Offset.Infinite,
                    end = Offset.Zero),
                shape = RoundedCornerShape(60.dp)
            )
            .clip(RoundedCornerShape(60.dp))
            .clickable(onClick = onClick, indication = ripple(), interactionSource = null)
    ) {
        Row(Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(icon),
                    tint = Color(0xFF8F9BB2),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(Modifier.width(4.dp))
            }
            Text(
                text = text,
                style = AppTheme.types.text,
                color = Color(0xFF8F9BB2),
                fontSize = 12.sp
            )
        }
    }

}