package com.vrsalex.uikit.component.v2

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Theme2Base
import com.vrsalex.uikit.util.getBase2Gradient
import com.vrsalex.uikit.util.getBase2_1Gradient

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppCheckbox(
    checked: Boolean,
    onChecked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val boxModifier = if (checked){
        Modifier
            .background(Theme2Base, RoundedCornerShape(3.dp))
            .background(Brush.linearGradient(
                0.0449f to Color(0x4DFFFFFF),
                0.9545f to Color(0x4DE9F0F7),
                start = Offset.Infinite,
                end = Offset.Zero
            ), RoundedCornerShape(3.dp))
            .border(
                width = 0.2.dp,
                shape = RoundedCornerShape(3.dp),
                brush = getBase2_1Gradient()
            )
            .innerShadow(
            shape = RoundedCornerShape(3.dp),
            shadow = Shadow(
                radius = 6.dp,
                spread = 0.dp,
                offset = DpOffset(2.dp, 2.dp),
                color = Color(0x338A9BBD)
            ))
            .innerShadow(
            shape = RoundedCornerShape(3.dp),
            shadow = Shadow(
                radius = 6.dp,
                spread = 0.dp,
                offset = DpOffset(-2.dp, -2.dp),
                color = Color(0xB2FFFFFF)
            )
        )
    } else {
        Modifier
            .dropShadow(
                shape = RoundedCornerShape(3.dp),
                shadow = Shadow(
                    radius = 12.dp,
                    spread = 0.dp,
                    offset = DpOffset(-2.dp, -2.dp),
                    color = Color(0xE5FFFFFF)
                ))
            .dropShadow(
                shape = RoundedCornerShape(3.dp),
                shadow = Shadow(
                    radius = 6.dp,
                    spread = 0.dp,
                    offset = DpOffset(2.dp, 2.dp),
                    color = Color(0x4D8A9BBD)
                )
            )
            .background(Theme2Base, RoundedCornerShape(3.dp))
            .background(Brush.linearGradient(
                0.0449f to Color(0x4DFFFFFF),
                0.9545f to Color(0x4DE9F0F7),
                start = Offset.Infinite,
                end = Offset.Zero
            ), RoundedCornerShape(3.dp))
            .border(
                width = 0.2.dp,
                shape = RoundedCornerShape(3.dp),
                brush = getBase2_1Gradient()
            )
    }

    Box(
        modifier = modifier
            .size(14.dp)
            .then(boxModifier)
            .clip(RoundedCornerShape(3.dp))
            .clickable(
                interactionSource = null,
                indication = ripple(),
                onClick = onChecked
            )
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ){

        Crossfade(checked) {
            if (it){
                Icon(
                    painter = painterResource(R.drawable.tick),
                    tint = null,
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
            }
        }

    }
}