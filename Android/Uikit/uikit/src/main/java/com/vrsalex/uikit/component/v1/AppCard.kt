package com.vrsalex.uikit.component.v1

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
import androidx.compose.runtime.mutableStateOf
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
 * Дата: 27.03.2026
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
            .height(169.dp)
            .width(315.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(getBaseGradient())
            .padding(horizontal = 24.dp).padding(top = 30.dp, bottom = 24.dp)
    ){
        Column(Modifier.fillMaxWidth(0.55f)) {
            Text(
                text = title,
                style = AppTheme.types.text.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = description,
                style = AppTheme.types.text.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = Color.White
                )
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.forward_arrow),
                contentDescription = null,
                tint = Color.White,
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
            modifier = Modifier.align(Alignment.BottomEnd).fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )

    }


}