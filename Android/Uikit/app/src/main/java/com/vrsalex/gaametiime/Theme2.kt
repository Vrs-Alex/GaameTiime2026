package com.vrsalex.gaametiime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.v2.AppTextInput
import com.vrsalex.uikit.component.v2.AppButton
import com.vrsalex.uikit.component.v2.AppCheckbox
import com.vrsalex.uikit.component.v2.AppPagination

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun Theme2() {

    Column(Modifier
        .fillMaxSize()
        .background(Color(0XFFE9F0F7))
        .verticalScroll(rememberScrollState())
        .safeDrawingPadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        AppButton(
            "Log in",
            R.drawable.lock,
            {}
        )


        var text by remember { mutableStateOf("") }
        AppTextInput(
            value = text,
            onValueChange = { text = it },
            placeholder = "Input field"
        )

        AppPagination(rememberPagerState(0) { 3 })

        var checked by remember { mutableStateOf(false) }
        AppCheckbox(checked, { checked = !checked })

    }
}