package com.vrsalex.gaametiime.presentation.feature.schedule

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vrsalex.gaametiime.R
import com.vrsalex.gaametiime.presentation.feature.common.BlurScreen
import com.vrsalex.uikit.component.v1.AppButton
import com.vrsalex.uikit.component.v1.AppCheckbox
import com.vrsalex.uikit.component.v1.AppDateSelect
import com.vrsalex.uikit.component.v1.AppTextInput
import com.vrsalex.uikit.theme.AppTheme

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */

@Composable
fun ScheduleScreen(
    viewmodel: ScheduleViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onStat: () -> Unit
) {
    val state by viewmodel.sate.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewmodel.channel.collect {
            when(it) {
                ScheduleContract.Effect.Back -> onBack()
                ScheduleContract.Effect.OnStatistic -> onStat()
            }
        }
    }

    ScheduleContent(state, viewmodel)
    if (state.isSuccess){
        BlurScreen(
            title = "Published Successful",
            description = "Wanna change/edit your scheduled game before it begins?",
            buttonContent = {
                AppButton(
                    "Statictic",
                    { viewmodel.sendEffect(ScheduleContract.Effect.OnStatistic) }
                )
            },
            onBack = {
                viewmodel.sendEffect(ScheduleContract.Effect.Back)
            }
        )
    }
}


@Composable
private fun ScheduleContent(state: ScheduleContract.State, viewmodel: ScheduleViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .blur(if (state.isSuccess) 16.dp else 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        Column(Modifier.padding(horizontal = 30.dp)) {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                tint = null,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp, 14.dp)
                    .clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = { viewmodel.sendEffect(ScheduleContract.Effect.Back) }
                    )
            )
            Spacer(Modifier.height(35.dp))
            Text(
                text = "Schedule Game",
                style = AppTheme.types.text.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = AppTheme.colors.redElemment
                )
            )
            Spacer(Modifier.height(30.dp))
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                AppTextInput(
                    value = state.gameName,
                    onValueChange = { viewmodel.updateData { copy(gameName = it) } },
                    placeholder = "Game Name"
                )
                AppTextInput(
                    value = state.category,
                    onValueChange = { viewmodel.updateData { copy(category = it) } },
                    placeholder = "Category"
                )
                AppTextInput(
                    value = state.price,
                    onValueChange = { viewmodel.updateData { copy(price = it) } },
                    placeholder = "Winning Price"
                )
            }
            Spacer(Modifier.height(55.dp))
            Row() {
                AppDateSelect(
                    onSelectDate = {},
                    header = "FROM"
                )
                //
            }
            Spacer(Modifier.height(18.dp))
            Row() {
                AppDateSelect(
                    onSelectDate = {},
                    header = "TO"
                )
            }
            Spacer(Modifier.height(106.dp))
            AppTextInput(
                value = state.description,
                onValueChange = { viewmodel.updateData { copy(description = it) } },
                placeholder = "Description"
            )
        }
        Spacer(Modifier.height(63.dp))
        Row(Modifier.padding(horizontal = 21.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "REMINDERS",
                style = AppTheme.types.text.copy(
                    fontSize = 12.sp,
                    color = AppTheme.colors.redElemment
                )
            )
            Spacer(Modifier.weight(1f))
            AppCheckbox(
                checked = state.isNotify,
                onChecked = { viewmodel.updateData { copy(isNotify = !state.isNotify) } }
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "Notification",
                style = AppTheme.types.text.copy(
                    fontSize = 12.sp
                )
            )
        }
        Spacer(Modifier.height(44.dp))
        AppButton(
            text = "Publish",
            { viewmodel.updateData { copy(isSuccess = true) } },
            modifier = Modifier.width(224.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(25.dp))
    }

}