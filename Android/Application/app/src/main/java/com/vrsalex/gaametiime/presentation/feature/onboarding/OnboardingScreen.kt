package com.vrsalex.gaametiime.presentation.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.vrsalex.uikit.component.v1.AppButton
import com.vrsalex.uikit.component.v1.AppPagination
import com.vrsalex.uikit.theme.AppTheme

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

@Composable
fun OnboardingScreen(
    viewmodel: OnBoardingViewmodel = hiltViewModel(),
    onAction: () -> Unit
) {

    val pagerState = rememberPagerState(0) { viewmodel.pages.size }
    Column(
        Modifier.fillMaxSize().safeDrawingPadding().padding(bottom = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingContent(viewmodel.pages[page]){
                onAction()
            }
        }
        AppPagination(pagerState, modifier = Modifier)
    }

}

@Composable
private fun OnboardingContent(state: OnboardingContract.State, onAction: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Image(
            painter = painterResource(state.imageId),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.8f),
            contentScale = ContentScale.FillWidth
        )

        Column(Modifier.padding(horizontal = 75.dp)) {
            Text(
                text = state.title,
                style = AppTheme.types.text,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.redElemment,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(36.dp))

            Text(
                text = state.description,
                style = AppTheme.types.text,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }


        if (state.mainButtonText != null) {
            AppButton(state.mainButtonText, { onAction() }, modifier = Modifier.width(224.dp))
        } else {
            Text(
                text = "Skip",
                style = AppTheme.types.text,
                modifier = Modifier.clickable { onAction() },
                color = AppTheme.colors.redElemment,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}