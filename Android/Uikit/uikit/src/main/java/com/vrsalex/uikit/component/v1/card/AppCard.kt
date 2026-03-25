package com.vrsalex.uikit.component.v1.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
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
import com.vrsalex.uikit.R
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppCard(
    title: String,
    description: String,
    imageId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .width(315.dp)
            .height(169.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(getBaseGradient())
    ){
        Column(
            Modifier.fillMaxHeight()
                .fillMaxWidth(0.6f)
                .padding(start = 24.dp, top = 30.dp, bottom = 24.dp)
        ) {
            Text(
                text = title,
                style = AppTheme.types.text,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.White
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = description,
                style = AppTheme.types.text,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = Color.White
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.forward_arrow),
                tint = null,
                contentDescription = null,
                modifier = Modifier.clickable(
                    onClick = onClick,
                    indication = ripple(),
                    interactionSource = null
                )
            )
        }

        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 22.dp, top = 38.dp, bottom = 24.dp)
                .align(Alignment.BottomEnd)
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

    }

}