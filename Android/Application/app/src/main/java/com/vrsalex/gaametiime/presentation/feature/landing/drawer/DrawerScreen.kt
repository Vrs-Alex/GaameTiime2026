package com.vrsalex.gaametiime.presentation.feature.landing.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vrsalex.gaametiime.R
import com.vrsalex.uikit.component.v1.AppTertiaryButton
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun DrawerScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier.fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        Row(
            Modifier.fillMaxWidth()
                .padding(top = 24.dp, end = 44.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(R.drawable.close_icon),
                tint = null,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable(
                        onClick = { onClose() }
                    )
                    .padding(2.dp)

            )
        }
        Column(
            Modifier
                .padding(start = 60.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(13.dp)) {
                AsyncImage(
                    model = "userAvatarUrl",
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.ellipse),
                    fallback = painterResource(R.drawable.ellipse),
                    placeholder = painterResource(R.drawable.ellipse)
                )
                Column() {
                    Text(
                        text = "Stone Stellar",
                        style = AppTheme.types.text.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = AppTheme.colors.redElemment
                        )
                    )
                    Text(
                        text = "Gold Player",
                        style = AppTheme.types.text.copy(
                            fontSize = 10.sp,
                            color = Color(0xFFF4C73E)
                        )
                    )
                }
            }
            Spacer(Modifier.height(60.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(34.dp)
            ) {

            }
        }
        Spacer(Modifier.weight(1f))
        Row(
            Modifier.fillMaxWidth().padding(bottom = 45.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AppTertiaryButton(
                text = "Logout",
                iconId = com.vrsalex.uikit.R.drawable.logout,
                onClick = { }
            )
        }
    }

}