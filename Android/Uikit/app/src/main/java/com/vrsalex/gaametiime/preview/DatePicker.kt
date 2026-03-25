package com.vrsalex.gaametiime.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.component.v1.controller.AppDatePicker
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker() {
    var showSheet by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()


    val formatter = remember {
        DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy", Locale.ENGLISH)
    }

    val dateText = remember(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { millis ->
            val instant = Instant.ofEpochMilli(millis)
            val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            date.format(formatter).uppercase()
        } ?: "SELECT DATE"
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AppDatePicker(
            label = "FROM",
            selectedDate = dateText,
            onClick = { showSheet = true }
        )
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TextButton(
                onClick = { showSheet = false },
                modifier = Modifier.align(Alignment.End).padding(end = 16.dp)
            ) {
                Text("DONE")
            }
        }
    }
}