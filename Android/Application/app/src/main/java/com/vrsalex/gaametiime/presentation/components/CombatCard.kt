package com.vrsalex.gaametiime.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.gaametiime.domain.combat.Combat
import com.vrsalex.gaametiime.domain.combat.CombatStatus
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */

@Composable
fun CombatCard(
    combat: Combat,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, getBaseGradient()),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 17.dp, top = 14.dp, end = 9.dp, bottom = 11.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                PlayerAvatar(player = combat.host, label = "Host")

                Text(
                    text = "VS",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    style = AppTheme.types.text.copy(
                        color = AppTheme.colors.redElemment,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )

                combat.guests.firstOrNull()?.let { guest ->
                    PlayerAvatar(player = guest, label = "Guest", isHost = false)
                }
                if (combat.guests.size > 1){
                    Text(
                        text = "+ ${combat.guests.size - 1}",
                        style = AppTheme.types.text.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp,
                            color = AppTheme.colors.redElemment
                        )
                    )
                }
            }

            Row (
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.weight(1f)
            ) {
                InfoColumn("Game Name:", combat.gameName, isGameName = true)
                InfoColumn("Status:", combat.status.name, status = combat.status)
                InfoColumn("Winning Price:", combat.winningPrice)
            }
        }
    }
}

@Composable
private fun InfoColumn(
    label: String,
    value: String,
    isGameName: Boolean = false,
    status: CombatStatus? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 6.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = value,
            fontSize = 6.sp,
            fontWeight = FontWeight.Bold,
            color = when {
                isGameName -> AppTheme.colors.redElemment
                status == CombatStatus.OPEN -> AppTheme.colors.redElemment
                else -> AppTheme.colors.redElemment
            }
        )
    }
}