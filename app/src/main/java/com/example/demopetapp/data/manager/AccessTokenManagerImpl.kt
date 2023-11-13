package com.example.demopetapp.data.manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.demopetapp.domain.manager.AccessTokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val AUTHENTICATION_TOKEN = "authentication_token"
class AccessTokenManagerImpl @Inject constructor(
    private val preferencesDataStore: DataStore<Preferences>
) : AccessTokenManager {

    private val authenticationKey = stringPreferencesKey(AUTHENTICATION_TOKEN)

    override suspend fun getCurrentToken() = preferencesDataStore.data
        .map { prefs -> prefs[authenticationKey] }
        .first()

    override suspend fun setToken(token: String) {
        preferencesDataStore.edit { prefs ->
            prefs[authenticationKey] = token
        }
    }
}
