package com.vrsalex.gaametiime.presentation.feature.game

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt
import kotlin.random.Random

// Данные о круге
data class CircleItem(
    val id: Int,       // Порядковый номер (0 - самый большой, 6 - самый маленький)
    val radiusDp: Int, // Размер круга в DP
    val offset: Offset, // Координаты верхнего левого угла на экране
    val color: Color = Color(0xFFFF4F7D) // Основной цвет контура
)

@Composable
fun CircleScreen() {
    // Используем BoxWithConstraints, чтобы получить точную ширину и высоту доступного экрана
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize() // На весь экран
            .background(Color.White) // Белый фон
    ) {
        val density = LocalDensity.current // Нужно для перевода DP в пиксели
        val screenWidthPx = constraints.maxWidth.toFloat()  // Ширина экрана в PX
        val screenHeightPx = constraints.maxHeight.toFloat() // Высота экрана в PX

        // Координаты центра экрана (куда мы будем собирать круги)
        val targetCenter = Offset(screenWidthPx / 2, screenHeightPx / 2)

        val circleCount = 7 // Сколько всего будет кругов
        val baseRadius = 140 // Радиус самого большого круга в DP
        val step = 15 // На сколько DP каждый следующий круг будет меньше предыдущего

        var isWin by remember { mutableStateOf(false) }

        // Список кругов. Используем mutableStateListOf для отслеживания изменений позиций
        val circles = remember { mutableStateListOf<CircleItem>() }

        // Эффект, который сработает один раз при открытии экрана
        LaunchedEffect(Unit) {
            if (circles.isEmpty()) { // Если список пуст — генерируем круги
                for (i in 0 until circleCount) {
                    val rDp = baseRadius - (i * step) // Вычисляем радиус для текущего ID
                    val rPx = with(density) { rDp.dp.toPx() } // Переводим радиус в пиксели

                    // Генерируем случайную позицию так, чтобы круг не вылезал за границы экрана
                    val randomX = Random.nextFloat() * (screenWidthPx - rPx * 2)
                    val randomY = Random.nextFloat() * (screenHeightPx - rPx * 2)

                    circles.add(
                        CircleItem(
                            id = i,
                            radiusDp = rDp,
                            offset = Offset(randomX, randomY)
                        )
                    )
                }
            }
        }

        val context = LocalContext.current
        LaunchedEffect(isWin) {
            if (isWin) {
                Toast.makeText(
                    context,
                    "WIN",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Проходим циклом по всем созданным кругам и рисуем их
        circles.forEachIndexed { index, circle ->
            val radiusPx = with(density) { circle.radiusDp.dp.toPx() } // Текущий радиус в пикселях

            Box(
                modifier = Modifier
                    .offset {
                        // Устанавливаем позицию круга на экране
                        IntOffset(
                            circle.offset.x.roundToInt(),
                            circle.offset.y.roundToInt()
                        )
                    }
                    .size((circle.radiusDp * 2).dp) // Размер Box равен диаметру круга
                    // ИСПРАВЛЕНИЕ: Чем больше ID (меньше круг), тем выше zIndex.
                    // Теперь маленькие круги сверху и легко нажимаются.
                    .zIndex(circle.id.toFloat() + 1f)
                    .clip(CircleShape) // Обрезаем содержимое по форме круга
                    .border(4.dp, circle.color, CircleShape) // Рисуем только розовую рамку
                    .pointerInput(circle.id) { // Обработка касаний для конкретного круга
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume() // Поглощаем событие, чтобы скролл не дергался
                                // Обновляем позицию в списке (перезаписываем элемент новым объектом)
                                circles[index] = circles[index].copy(
                                    offset = circles[index].offset + dragAmount
                                )
                            },
                            onDragEnd = {
                                // Когда отпустили палец, проверяем расстояние до центра
                                val currentCircleCenter = Offset(
                                    circles[index].offset.x + radiusPx,
                                    circles[index].offset.y + radiusPx
                                )

                                // Вычисляем расстояние между центром круга и центром экрана
                                val distanceToTarget = (currentCircleCenter - targetCenter).getDistance()

                                // Если расстояние меньше 80 пикселей — "примагничиваем" круг к центру
                                if (distanceToTarget < 80f) {
                                    circles[index] = circles[index].copy(
                                        offset = Offset(
                                            targetCenter.x - radiusPx,
                                            targetCenter.y - radiusPx
                                        )
                                    )
                                }

                                val allInCenter = circles.all { circle ->
                                    val rPx = with(density) { circle.radiusDp.dp.toPx() }
                                    val cCenter = Offset(circle.offset.x + rPx, circle.offset.y + rPx)

                                    // Расстояние должно быть почти нулевым (допускаем погрешность в 1 пиксель)
                                    (cCenter - targetCenter).getDistance() < 1f
                                }

                                if (allInCenter) {
                                    isWin = true
                                }
                            }
                        )
                    }
            )
        }

        // Рисуем маленькую серую точку точно в центре экрана как ориентир
        Box(
            modifier = Modifier
                .size(4.dp)
                .offset {
                    IntOffset(
                        (targetCenter.x - 2.dp.toPx()).roundToInt(),
                        (targetCenter.y - 2.dp.toPx()).roundToInt()
                    )
                }
                .background(Color.Gray.copy(alpha = 0.2f), CircleShape)
        )
    }
}