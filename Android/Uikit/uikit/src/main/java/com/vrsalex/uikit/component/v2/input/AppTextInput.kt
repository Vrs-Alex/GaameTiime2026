package com.vrsalex.uikit.component.v2.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Theme2Back
import com.vrsalex.uikit.util.getBaseGradient2

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Theme2Back, RoundedCornerShape(30.dp))
            .background(
                brush = getBaseGradient2(Color(0x12121212)),
                RoundedCornerShape(30.dp)
            ).border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    0.0499f to Color(0xFFE9F0F7),
                    0.7567f to Color(0x40FFFFFF),
                    start = Offset.Infinite,
                    end = Offset.Zero
                ),
                RoundedCornerShape(30.dp)
            ).innerShadow(
                shape = RoundedCornerShape(30.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 0.dp,
                    offset = DpOffset(3.dp, 3.dp),
                    color = Color(0x668A9BBD)
                )
            ).innerShadow(
                shape = RoundedCornerShape(30.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 0.dp,
                    offset = DpOffset((-3).dp, (-3).dp),
                    color = Color(0xB2FFFFFF)
                )
            ).padding(start = 10.dp, end = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = AppTheme.types.text.copy(fontSize = 10.sp),
            decorationBox = { innerTextField ->
                if (value.isEmpty()){
                    Text(
                        text = placeHolder,
                        style = AppTheme.types.text.copy(
                            brush = Brush.verticalGradient(
                                listOf(Color(0xFFEB0DFE), Color(0xFF11CADE))
                            )
                        ),
                        fontSize = 10.sp,
                    )
                }
                innerTextField()
            }
        )

        trailingIcon?.let {
            it()
        }

    }

}