package com.vrsalex.uikit.component.v1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme

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
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {

    var visiblePassword by remember { mutableStateOf(true) }
    val visualTransformation = remember(isPassword && visiblePassword) {
        if (isPassword && !visiblePassword) PasswordVisualTransformation()
        else VisualTransformation.None
    }

    Column(
        modifier = modifier
            .height(26.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            visualTransformation = visualTransformation,
            textStyle = AppTheme.types.text.copy(
                fontSize = 12.sp,
                color = Color.Black
            ),
            decorationBox = { innerField ->
                Row() {
                    Box(Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = AppTheme.types.text.copy(
                                    fontSize = 12.sp,
                                    color = Color.Black
                                )
                            )
                        }
                        innerField()
                    }
                    // Password
                }
            }
        )
        Spacer(Modifier.height(8.dp))
        Box(Modifier.height(1.dp).fillMaxWidth().background(AppTheme.colors.redElemment))
    }


}