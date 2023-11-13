package com.example.demopetapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrefsModule {

    private const val AUTHENTICATION_PREFERENCES = "authentication_preferences"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        AUTHENTICATION_PREFERENCES
    )

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.dataStore
}
