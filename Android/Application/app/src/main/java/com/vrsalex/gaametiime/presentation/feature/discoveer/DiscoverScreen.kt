package com.vrsalex.gaametiime.presentation.feature.discoveer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vrsalex.gaametiime.R
import com.vrsalex.gaametiime.presentation.components.CombatCard
import com.vrsalex.gaametiime.presentation.feature.schedule.ScheduleContract
import com.vrsalex.gaametiime.presentation.navigation.DiscoverDestination
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.util.getBaseGradient

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    DiscoverContent(state, viewModel)
}

@Composable
private fun DiscoverContent(state: DiscoverContract.State, viewModel: DiscoverViewModel) {

    Column(
        Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 150.dp)
        ) {

            item(contentType = "header") {
                Column(
                    Modifier.fillMaxWidth()
                        .background(getBaseGradient())
                        .statusBarsPadding()
                        .padding(30.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.back_icon),
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp, 14.dp)
                            .clickable(
                                interactionSource = null,
                                indication = null,
                                onClick = { viewModel.sendEffect(DiscoverContract.Effect.OnBack) }
                            )
                    )
                    Spacer(Modifier.height(36.dp))
                    Box(Modifier.fillMaxWidth().height(66.dp)) {
                        Text(
                            text = "Discover \nCombats",
                            style = AppTheme.types.text.copy(
                                fontSize = 22.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.align(Alignment.BottomStart)
                        )
                        Text(
                            text = "FILTER",
                            style = AppTheme.types.text.copy(
                                fontSize = 12.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.align(Alignment.BottomEnd)
                        )
                    }
                    Spacer(Modifier.height(24.dp))
                    BasicTextField(
                        value = state.searchQuery,
                        onValueChange = { viewModel.updateSearch(it) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(41.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(Color.White),
                        textStyle = AppTheme.types.text.copy(
                            fontSize = 12.sp,
                            color = Color.Black
                        ),
                        decorationBox = { innerField ->
                            Row(Modifier.fillMaxSize().padding(horizontal = 18.dp, vertical = 12.dp)) {
                                Icon(
                                    painter = painterResource(R.drawable.search_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = null
                                )
                                Spacer(Modifier.width(8.dp))
                                Box(Modifier.weight(1f)){
                                    if (state.searchQuery.isEmpty()){
                                        Text(
                                            text = "Search Combat",
                                            style = AppTheme.types.text.copy(
                                                fontSize = 12.sp,
                                                color = Color(0xFFC9C9C9)
                                            )
                                        )
                                    }
                                    innerField()
                                }
                            }
                        }
                    )
                }
            }

            item(contentType = "trending_header") {
                Row(
                    Modifier.padding(horizontal = 30.dp, vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Trending this week",
                        style = AppTheme.types.text.copy(
                            fontSize = 12.sp,
                            color = AppTheme.colors.redElemment,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "View All",
                        style = AppTheme.types.text.copy(
                            fontSize = 8.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(R.drawable.access_icon),
                        contentDescription = null,
                        modifier = Modifier.size(7.dp)
                    )
                }
            }

            items(state.trendingCombats, key = { it.id }){ combat ->
                CombatCard(
                    combat = combat,
                    onClick = { viewModel.sendEffect(DiscoverContract.Effect.OnOpenCombat(combat.id)) },
                    modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 10.dp)
                )
            }



            item(contentType = "latest_header") {
                Row(
                    Modifier.padding(horizontal = 30.dp, vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Latest Combats",
                        style = AppTheme.types.text.copy(
                            fontSize = 12.sp,
                            color = AppTheme.colors.redElemment,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "View All",
                        style = AppTheme.types.text.copy(
                            fontSize = 8.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(R.drawable.access_icon),
                        contentDescription = null,
                        modifier = Modifier.size(7.dp)
                    )
                }
            }

            items(state.latestCombats, key = { it.id + "_latest" }){ combat ->
                CombatCard(
                    combat = combat,
                    onClick = { viewModel.sendEffect(DiscoverContract.Effect.OnOpenCombat(combat.id)) },
                    modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 10.dp)
                )
            }
        }
    }

}


