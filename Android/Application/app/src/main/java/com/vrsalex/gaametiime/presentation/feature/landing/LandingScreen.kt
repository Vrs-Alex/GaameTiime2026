package com.vrsalex.gaametiime.presentation.feature.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.vrsalex.gaametiime.R
import com.vrsalex.uikit.component.v1.AppCard
import com.vrsalex.uikit.theme.AppTheme
import kotlinx.coroutines.launch

/**
 * Назначение:
 * Дата: 26.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun LandingScreen(
    viewModel: LandingViewModel = hiltViewModel(),
    onDrawerOpen: () -> Unit,
    navTo: (Any) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.channel.collect {
            when(it) {
                is LandingContract.Effect.NavigateTo -> {
                    navTo(it.payload)
                }
            }
        }
    }

    LandingContent(
        state = state,
        onMenuClick = {
            onDrawerOpen()
        },
        viewModel
    )

}



@Composable
private fun LandingContent(
    state: LandingContract.State,
    onMenuClick: () -> Unit,
    viewModel: LandingViewModel
) {
    Column(Modifier.fillMaxSize().safeDrawingPadding()) {
        Row(
            Modifier.fillMaxWidth()
                .padding(horizontal = 30.dp).padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.hamburger),
                tint = null,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onMenuClick)
                    .padding(7.dp)
            )
            Spacer(Modifier.weight(1f))
            AsyncImage(
                model = "userAvatarUrl",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ellipse),
                fallback = painterResource(R.drawable.ellipse),
                placeholder = painterResource(R.drawable.ellipse)
            )
            Spacer(Modifier.width(2.dp))
            Text(
                text = state.name,
                style = AppTheme.types.text.copy(fontSize = 10.sp)
            )
        }

        LazyColumn(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(top = 35.dp, bottom = 135.dp)
        ) {

            items(state.items) { item ->
                AppCard(
                    title = item.title,
                    description = item.description,
                    imageId = item.imageId,
                    onClick = { viewModel.navTo(item.navTo) }
                )
            }

        }
    }
}