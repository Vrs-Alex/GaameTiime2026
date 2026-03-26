package com.vrsalex.gaametiime.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("setting")

@Singleton
class DataStoreManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) {

    fun getIsFirstStart(): Flow<Boolean> =
        context.dataStore.data.map {
            it[IS_FIRST_START] ?: true
        }

    suspend fun markFirstStart() =
        context.dataStore.edit {
            it[IS_FIRST_START] = false
        }


    companion object {
        val IS_FIRST_START = booleanPreferencesKey("isFirstStart")

    }
}