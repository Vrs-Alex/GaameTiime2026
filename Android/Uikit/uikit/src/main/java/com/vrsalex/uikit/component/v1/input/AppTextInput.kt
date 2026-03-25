package com.vrsalex.uikit.component.v1.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
fun AppTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    modifier: Modifier = Modifier
) {
    val focus = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(26.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
                .focusRequester(focus),
            singleLine = true,
            textStyle = AppTheme.types.text.copy(fontSize = 12.sp),
            decorationBox = { innerTextField ->
                if (value.isEmpty()){
                    Text(
                        placeHolder,
                        style = AppTheme.types.text,
                        fontSize = 12.sp
                    )
                }
                innerTextField()
            }
        )
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier.fillMaxWidth().height(1.dp)
                .background(getBaseGradient())
        )
    }
}