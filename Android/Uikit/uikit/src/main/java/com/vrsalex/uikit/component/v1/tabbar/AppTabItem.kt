package com.vrsalex.uikit.component.v1.tabbar

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
data class AppTabItem<T>(
    val payload: T,
    val title: String,
    val iconId: Int
)
