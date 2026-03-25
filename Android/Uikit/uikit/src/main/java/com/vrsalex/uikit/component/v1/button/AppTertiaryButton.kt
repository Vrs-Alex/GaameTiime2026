package com.vrsalex.uikit.component.v1.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.GaameTiimeTheme

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppTertiaryButton(
    text: String,
    iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.height(24.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = null
            ),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconId),
            tint = null,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            style = AppTheme.types.text,
            fontSize = 16.sp
        )
    }

}


@Preview
@Composable
private fun PreviewButton(){
    GaameTiimeTheme { 
        AppTertiaryButton(
            text = "Logout",
            iconId = R.drawable.logout,
            { }
        )
    }
}