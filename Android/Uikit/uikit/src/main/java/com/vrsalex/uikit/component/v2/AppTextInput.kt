package com.vrsalex.uikit.component.v2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Theme2Base
import com.vrsalex.uikit.util.getBase2Gradient

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .height(30.dp)
            .width(220.dp)
            .background(Theme2Base, RoundedCornerShape(60.dp))
            .background(Brush.linearGradient(
                0.0449f to Color(0x4DFFFFFF),
                0.9545f to Color(0x4DE9F0F7),
                start = Offset.Infinite,
                end = Offset.Zero
            ), RoundedCornerShape(60.dp)
            ).border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    0.0499f to Theme2Base,
                    0.7567f to Color(0x40FFFFFF),
                    start = Offset.Infinite,
                    end = Offset.Zero
                ),
                shape = RoundedCornerShape(60.dp)
            ).innerShadow(
                shape = RoundedCornerShape(60.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 0.dp,
                    offset = DpOffset(3.dp, 3.dp),
                    color = Color(0x668A9BBD)
                )
            ).innerShadow(
                shape = RoundedCornerShape(60.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 0.dp,
                    offset = DpOffset((-3).dp, (-3).dp),
                    color = Color(0xB2FFFFFF)
                )
            ),
        textStyle = AppTheme.types.text.copy(
            fontSize = 10.sp,
            color = Color.Black
        ),
        decorationBox = { innerField ->
            Box(
                Modifier.padding(start = 10.dp, top = 9.dp, end = 13.dp, bottom = 7.dp)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = AppTheme.types.text.copy(
                            brush = getBase2Gradient(),
                            fontSize = 10.sp
                        )
                    )
                }
                innerField()
            }
        }
    )


}