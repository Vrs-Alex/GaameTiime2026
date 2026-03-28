package com.vrsalex.gaametiime.presentation.feature.game

import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

// Модель данных для одного кусочка пазла
data class PuzzleData(
    val originalIndex: Int, // Правильный порядковый номер (нужен для проверки победы и как уникальный ключ)
    val image: Bitmap       // Обрезанная часть картинки
)

// Функция для разрезания картинки на сетку (по умолчанию 3x3)
fun cutImage(image: Bitmap, rows: Int = 3, cols: Int = 3): List<PuzzleData> {
    val width = image.width / cols  // Вычисляем ширину одного фрагмента
    val height = image.height / rows // Вычисляем высоту одного фрагмента
    return buildList {
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                // Добавляем новый кусочек, вырезая его из исходного Bitmap
                add(
                    PuzzleData(
                        originalIndex = r * cols + c, // Присваиваем индекс от 0 до 8
                        image = Bitmap.createBitmap(image, width * c, height * r, width, height)
                    )
                )
            }
        }
    }
}

@Composable
fun PuzzleScreen(image: Bitmap) {
    val puzzlePieces = remember(image) {
        mutableStateListOf<PuzzleData>().apply {
            addAll(cutImage(image).shuffled())
        }
    }

    // Храним ID (originalIndex) того элемента, который сейчас тянет пользователь
    var draggedItemKey by remember { mutableStateOf<Int?>(null) }
    // Храним смещение пальца относительно начальной позиции элемента
    var draggingOffset by remember { mutableStateOf(Offset.Zero) }

    // Состояние сетки (нужно, чтобы понимать, где физически находятся другие элементы)
    val gridState = rememberLazyGridState()

    var isWin by remember { mutableStateOf(false) }

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

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Всегда 3 колонки
        state = gridState,            // Привязываем состояние
        contentPadding = PaddingValues(16.dp), // Отступы от краев экрана
        verticalArrangement = Arrangement.spacedBy(1.dp),   // Расстояние между строками
        horizontalArrangement = Arrangement.spacedBy(1.dp), // Расстояние между колонками
        modifier = Modifier.fillMaxSize()                   // Занимаем весь экран
    ) {
        itemsIndexed(
            items = puzzlePieces,
            key = { _, item -> item.originalIndex } // Ключ по ID позволяет Compose анимировать перемещение
        ) { index, item ->

            val isDragging = draggedItemKey == item.originalIndex

            val itemModifier = if (isDragging) {
                Modifier
                    .zIndex(10f)
                    .graphicsLayer {
                        translationX = draggingOffset.x
                        translationY = draggingOffset.y
                        scaleX = 1.1f // Слегка увеличиваем для эффекта "взял в руку"
                        scaleY = 1.1f
                    }
            } else {
                Modifier
                    .animateItem()
                    .zIndex(1f)    // Обычный приоритет отрисовки
            }

            Box(
                modifier = Modifier
                    .aspectRatio(1f) // Квадратная ячейка
                    .then(itemModifier) // Применяем динамические модификаторы выше
                    .pointerInput(item.originalIndex) { // Обработка жестов
                        detectDragGesturesAfterLongPress( // Начинаем только после долгого нажатия
                            onDragStart = {
                                draggedItemKey = item.originalIndex // Фиксируем, что начали тащить этот ID
                            },
                            onDrag = { change, dragAmount ->
                                change.consume() // Помечаем событие как обработанное
                                draggingOffset += dragAmount // Обновляем координаты пальца

                                // Ищем текущий порядковый индекс перетаскиваемого элемента в списке
                                val currentIdx = puzzlePieces.indexOfFirst { it.originalIndex == item.originalIndex }
                                if (currentIdx == -1) return@detectDragGesturesAfterLongPress

                                val layoutInfo = gridState.layoutInfo
                                // Находим информацию о расположении нашего элемента на экране
                                val draggedItemInfo = layoutInfo.visibleItemsInfo.find { it.key == item.originalIndex }

                                if (draggedItemInfo != null) {
                                    // Вычисляем, где сейчас находится центр перетаскиваемой картинки
                                    val currentCenter = Offset(
                                        draggedItemInfo.offset.x + draggedItemInfo.size.width / 2f + draggingOffset.x,
                                        draggedItemInfo.offset.y + draggedItemInfo.size.height / 2f + draggingOffset.y
                                    )

                                    // Ищем, над какой другой ячейкой сейчас находится этот центр
                                    val targetItem = layoutInfo.visibleItemsInfo.find { other ->
                                        if (other.key == item.originalIndex) return@find false
                                        val left = other.offset.x.toFloat()
                                        val top = other.offset.y.toFloat()
                                        // Проверка вхождения точки центра в границы другой ячейки
                                        currentCenter.x in left..left + other.size.width &&
                                                currentCenter.y in top..top + other.size.height
                                    }

                                    if (targetItem != null) {
                                        // Находим индекс ячейки-мишени
                                        val targetIdx = puzzlePieces.indexOfFirst { it.originalIndex == targetItem.key }
                                        if (targetIdx != -1) {
                                            // Магия: корректируем смещение, чтобы картинка не прыгала при смене индекса
                                            draggingOffset += Offset(
                                                (draggedItemInfo.offset.x - targetItem.offset.x).toFloat(),
                                                (draggedItemInfo.offset.y - targetItem.offset.y).toFloat()
                                            )
                                            // Меняем элементы местами в списке (это запустит анимацию у других ячеек)
                                            puzzlePieces.add(targetIdx, puzzlePieces.removeAt(currentIdx))
                                        }
                                    }
                                }
                            },
                            onDragEnd = {
                                draggedItemKey = null // Отпускаем элемент
                                draggingOffset = Offset.Zero // Сбрасываем смещение

                                val isOrdered = puzzlePieces.withIndex().all { (index, piece) ->
                                    piece.originalIndex == index
                                }

                                if (isOrdered) {
                                    isWin = true
                                }
                            },
                            onDragCancel = {
                                draggedItemKey = null // Сброс при отмене (например, системное прерывание)
                                draggingOffset = Offset.Zero
                            }
                        )
                    }
            ) {
                Image(
                    bitmap = item.image.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(), // Картинка на весь размер ячейки
                    contentScale = ContentScale.Crop    // Обрезаем края, если пропорции чуть не совпали
                )
            }
        }
    }
}