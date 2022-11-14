package com.pose.move.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class InternalStorageManager @Inject constructor(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(KEY_APP_PREFERENCES)
    private val dataStore get() = context.dataStore

    val isOnboardingComplete: Flow<Boolean>
        get() = dataStore.data.catchOrEmitEmpty().getValue(KEY_IS_ONBOARDING_COMPLETE, false)

    suspend fun setIsOnboardingComplete(value: Boolean) {
        dataStore.setValue(KEY_IS_ONBOARDING_COMPLETE, value)
    }

    val authenticationToken: Flow<String>
        get() = dataStore.data.catchOrEmitEmpty().getValue(KEY_AUTHENTICATION_TOKEN, "")

    suspend fun setAuthToken(value: String) {
        dataStore.setValue(KEY_AUTHENTICATION_TOKEN, value)
    }

    // TODO: Remove this after integrating actual auth flow API/DB calls
    val hasUserAddedLicensePhoto: Flow<Boolean>
        get() = dataStore.data.catchOrEmitEmpty().getValue(KEY_HAS_USER_ADDED_LICENSE_PHOTO, false)

    suspend fun setHasUserAddedLicensePhoto(value: Boolean) {
        dataStore.setValue(KEY_HAS_USER_ADDED_LICENSE_PHOTO, value)
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    companion object {
        private fun <T> Flow<Preferences>.getValue(key: Preferences.Key<T>, defaultValue: T) =
            map { preferences -> preferences[key] ?: defaultValue }

        private suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
            edit { preferences -> preferences[key] = value }
        }

        private fun Flow<Preferences>.catchOrEmitEmpty() = catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }

        private const val KEY_APP_PREFERENCES = "com.pose.move.KEY_MOVE_APP_PREFERENCES"

        private val KEY_IS_ONBOARDING_COMPLETE = booleanPreferencesKey("KEY_IS_ONBOARDING_COMPLETE")
        private val KEY_AUTHENTICATION_TOKEN = stringPreferencesKey("KEY_AUTHENTICATION_TOKEN")
        private val KEY_HAS_USER_ADDED_LICENSE_PHOTO = booleanPreferencesKey("KEY_HAS_USER_ADDED_LICENSE_PHOTO")
    }
}