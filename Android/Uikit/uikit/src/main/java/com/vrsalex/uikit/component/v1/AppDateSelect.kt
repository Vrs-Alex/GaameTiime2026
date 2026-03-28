package com.vrsalex.uikit.component.v1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.theme.AppTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDateSelect(
    onSelectDate: (LocalDate) -> Unit,
    header: String,
    modifier: Modifier = Modifier
) {

    var isVisibleBottomSheet by remember { mutableStateOf(false) }

    val datePicker = rememberDatePickerState()
    val format = remember {  DateTimeFormatter.ofPattern("EEE, MMM d, yyyy", Locale.ENGLISH) }


    val dateText = remember(datePicker.selectedDateMillis) {
        datePicker.selectedDateMillis?.let { mills ->
            val instant = Instant.ofEpochMilli(mills)
            val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            onSelectDate(date)
            date.format(format).uppercase()
        } ?: "DATE"
    }


    Column(
        modifier = modifier
            .height(40.dp)
            .width(147.dp)
            .clickable(
                indication = ripple(),
                interactionSource = null,
                onClick = { isVisibleBottomSheet = true }
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = header,
            style = AppTheme.types.text.copy(
                color = AppTheme.colors.redElemment,
                fontSize = 8.sp
            )
        )
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = dateText,
                style = AppTheme.types.text.copy(
                    fontSize = 12.sp, color = Color.Black
                )
            )
            Spacer(Modifier.width(16.dp))
            Icon(
                painter = painterResource(R.drawable.dropdown),
                contentDescription = null,
                tint = null,
                modifier = Modifier.size(8.dp)
            )
        }
    }


    if (isVisibleBottomSheet){
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onDismissRequest = {
                isVisibleBottomSheet = false
            }
        ) {
            DatePicker(datePicker)
        }
    }

}