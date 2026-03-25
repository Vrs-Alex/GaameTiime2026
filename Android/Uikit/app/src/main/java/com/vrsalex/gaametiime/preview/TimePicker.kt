package com.vrsalex.gaametiime.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.component.v1.controller.AppDatePicker
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTimePicker() {
    var showSheet by remember { mutableStateOf(false) }

    // Инициализируем текущим временем
    val currentTime = remember { java.time.LocalTime.now() }
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.hour,
        initialMinute = currentTime.minute,
        is24Hour = false // В макете было AM/PM, так что ставим false
    )

    val formatter = remember {
        DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
    }


    val timeText = remember(timePickerState.hour, timePickerState.minute) {
        val localTime = java.time.LocalTime.of(timePickerState.hour, timePickerState.minute)
        localTime.format(formatter).uppercase()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AppDatePicker(
            label = "",
            selectedDate = timeText,
            onClick = { showSheet = true }
        )
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Сам циферблат времени
                TimePicker(state = timePickerState)

                TextButton(
                    onClick = { showSheet = false },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("DONE")
                }
            }
        }
    }
}