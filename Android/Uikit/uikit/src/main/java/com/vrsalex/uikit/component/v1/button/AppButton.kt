package com.vrsalex.uikit.component.v1.button

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.GaameTiimeTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    Button(
        onClick = onClick,
        modifier = modifier
            .height(58.dp)
            .width(210.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(getBaseGradient()),
        contentPadding = PaddingValues(start = 7.dp, top = 18.dp, end = 7.dp, bottom = 19.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            style = AppTheme.types.text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ButtonPreview(){
    GaameTiimeTheme {
        AppButton("Let’s Combat!", {})
    }
}