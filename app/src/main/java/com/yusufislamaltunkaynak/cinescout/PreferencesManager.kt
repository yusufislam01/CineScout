package com.yusufislamaltunkaynak.cinescout

import android.app.Application // Hilt için Application sınıfınızda @HiltAndroidApp olmalı
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext // Hilt @ApplicationContext importu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

// DataStore'u top-level olarak tanımla
private const val USER_PREFERENCES_NAME = "user_prefs"

// ApplicationContext kullanılarak DataStore'a erişim sağlanır.
// Bu extension property'nin ApplicationContext ile kullanılması daha uygundur.
// Ancak PreferencesManager içinde @ApplicationContext ile zaten alacağımız için
// doğrudan PreferencesManager içinde DataStore instance'ını oluşturabiliriz ya da
// bu top-level tanımı kullanmaya devam edebiliriz. Hilt ile enjekte edilen context'i kullanmak daha temizdir.
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

@Singleton // Hilt için Singleton anotasyonu
class PreferencesManager @Inject constructor(
    // Hilt tarafından ApplicationContext enjekte edilecek
    @ApplicationContext private val appContext: Context
) {

    private companion object {
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        // Diğer preference key'leriniz buraya eklenebilir
    }

    // Artık appContext üzerinden dataStore'a erişiyoruz
    val isOnboardingCompleted: Flow<Boolean> = appContext.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("PreferencesManager", "Error reading onboarding status.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[ONBOARDING_COMPLETED] ?: false
        }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        try {
            appContext.dataStore.edit { preferences ->
                preferences[ONBOARDING_COMPLETED] = completed
            }
        } catch (exception: IOException) {
            Log.e("PreferencesManager", "Error writing onboarding status.", exception)
            // İsteğe bağlı hata yönetimi
        }
    }
}
