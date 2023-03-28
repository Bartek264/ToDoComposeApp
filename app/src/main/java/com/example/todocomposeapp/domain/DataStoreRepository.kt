package com.example.todocomposeapp.domain

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.domain.DataStoreKey.PREFERENCE_KEY
import com.example.todocomposeapp.domain.DataStoreKey.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context){

	private object PreferencesKey{
		val sortType = stringPreferencesKey(PREFERENCE_KEY)
	}

	private val dataStore = context.dataStore

	suspend fun setSortState(priority: Priority) {
		dataStore.edit { preferences ->
			preferences[PreferencesKey.sortType] = priority.name
		}
	}

	val readSortState: Flow<String> = dataStore.data.catch {exception ->
		if (exception is IOException) {
			emit(emptyPreferences())
		} else {
			throw exception
		}
	}.map { value: Preferences ->
		value[PreferencesKey.sortType] ?: Priority.NONE.name
	}

}

object DataStoreKey {
	const val PREFERENCE_NAME = "todo_preferences"
	const val PREFERENCE_KEY = "sort_state"
}