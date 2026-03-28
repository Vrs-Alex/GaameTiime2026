package com.vrsalex.gaametiime.presentation.feature.stats

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.VicoScrollState
import com.patrykandpatrick.vico.compose.cartesian.VicoZoomState
import com.patrykandpatrick.vico.compose.cartesian.Zoom
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.compose.cartesian.data.columnSeries
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.CartesianLayerPadding
import com.patrykandpatrick.vico.compose.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.vrsalex.uikit.theme.poppinsFont
import kotlin.math.max

/**
 * Назначение:
 * Дата: 27.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun GithubStatsScreen(
    viewModel: StatisticViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    when (state) {
        is GithubUiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...")
        }
        is GithubUiState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: ${state.message}", color = Color.Red)
        }
        is GithubUiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .safeDrawingPadding()
                    .padding(24.dp)
            ) {
                Text("Statistics", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF94C74))
                Spacer(Modifier.height(24.dp))

                // 1. КАРТОЧКА С VICO CHART
                EarningsCard(state.totalCommits, state.weekCommits)

                Spacer(Modifier.height(32.dp))

                // 2. КОЛЬЦЕВОЙ ГРАФИК (Played Games)
                Text("Public Repos", fontWeight = FontWeight.Bold, color = Color(0xFFF94C74))
                Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                    PlayedGamesDonut(state.publicPercent)
                }

                Spacer(Modifier.height(32.dp))

                // 3. СТОЛБЦЫ (Scheduled Games)
                Text("Weekly Activity", fontWeight = FontWeight.Bold, color = Color(0xFFF94C74))
                BarChart(state.weekCommits)
            }
        }
    }
}

@Composable
fun EarningsCard(total: Int, points: List<Int>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF94C74))
            .padding(20.dp)
    ) {
        Column {
            Text("THIS WEEK COMMITS", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            Text("$total.00", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

        // Линейный график из библиотеки Vico
        Box(modifier = Modifier.align(Alignment.CenterEnd).width(120.dp).height(60.dp)) {
            WeeklyCommitsLineChart(points)
        }
    }
}


@Composable
fun PlayedGamesDonut(percent: Float) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(160.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 22.dp.toPx()
            // Фон
            drawArc(Color(0xFFF0F0F0), 0f, 360f, false, style = Stroke(strokeWidth))
            // Синий акцент (статичный или тоже с GitHub)
            drawArc(Color(0xFF88C9F9), -90f, 70f, false, style = Stroke(strokeWidth + 10.dp.toPx(), cap = StrokeCap.Square))
            // Основной прогресс
            drawArc(
                color = Color(0xFFF18DAA),
                startAngle = -20f,
                sweepAngle = (percent / 100) * 250f,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Square)
            )
        }
        Text("${percent.toInt()}%", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
    }
}

@Composable
fun BarChart(data: List<Int>) {
    val max = (data.maxOrNull() ?: 1).coerceAtLeast(1)
    Row(
        Modifier.fillMaxWidth().height(140.dp).padding(horizontal = 62.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { value ->
            val ratio = value.toFloat() / max
            Box(
                Modifier.width(18.dp).fillMaxHeight().clip(RoundedCornerShape(10.dp)).background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(Modifier.fillMaxWidth().fillMaxHeight(ratio).background(Color(0xFFF18DAA)))
            }
        }
    }
}


@Composable
fun WeeklyCommitsLineChart(
    weekCommits: List<Int>,           // например: [3, 7, 2, 12, 8, 5, 9]
    modifier: Modifier = Modifier
) {
    if (weekCommits.isEmpty()) {
        Text("Нет данных за неделю", modifier = modifier)
        return
    }

    val modelProducer = remember { CartesianChartModelProducer() }

    // Обновляем данные при изменении списка
    LaunchedEffect(weekCommits) {
        modelProducer.runTransaction {
            lineSeries {
                series(
                    x = weekCommits.indices.toList(),
                    y = weekCommits
                )
            }
        }
    }

    val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                lineProvider = LineCartesianLayer.LineProvider.series(
                    LineCartesianLayer.rememberLine(
                        pointConnector = LineCartesianLayer.PointConnector.cubic(
                            curvature = 0.5f        // от 0.0 (прямые линии) до 1.0 (очень плавно)
                        )
                    )
                ),

                pointSpacing = 4.dp
            )
        ),
        zoomState = rememberVicoZoomState(),
        modelProducer = modelProducer,
        modifier = Modifier.width(130.dp).height(30.dp)
    )
}