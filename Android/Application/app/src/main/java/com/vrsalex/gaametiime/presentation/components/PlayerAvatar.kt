package com.vrsalex.gaametiime.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vrsalex.gaametiime.R
import com.vrsalex.gaametiime.domain.player.Player
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */

@Composable
fun PlayerAvatar(
    player: Player,
    label: String,
    modifier: Modifier = Modifier,
    isHost: Boolean = true
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                model = player.avatarUrl,
                contentDescription = null,
                error = painterResource(R.drawable.ellipse),
                placeholder = painterResource(R.drawable.ellipse),
                modifier = Modifier
                    .size(27.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Box(
                modifier = Modifier
                    .align(if (isHost) Alignment.TopStart else Alignment.TopEnd)
                    .offset(
                        x = if (isHost) (-8).dp else 8.dp,
                        y = (-8).dp
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(getBaseGradient())
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = AppTheme.types.text.copy(
                        fontSize = 4.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Spacer(Modifier.height(3.dp))
        Text(
            text = player.name,
            style = AppTheme.types.text.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 6.sp
            )
        )
    }
}