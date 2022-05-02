package com.adityashidlyali.datastorehilt

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("onBoardingStatus")

@Singleton //You can ignore this annotation as return `datastore` from `preferencesDataStore` is singleton
class DataStorePreferenceManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val onBoardingStatus = appContext.dataStore

    companion object {
        val onBoardingStatusPreferencesKey = booleanPreferencesKey("ONBOARD_STATUS")
    }

    suspend fun setOnBoardStatus(onBoardStatusBoolean: Boolean) {
        onBoardingStatus.edit { onBoardStatus ->
            onBoardStatus[onBoardingStatusPreferencesKey] = onBoardStatusBoolean
        }
    }

    val onBoardStatusFlow: Flow<Boolean> = onBoardingStatus.data.map { onBoardPreference ->
        onBoardPreference[onBoardingStatusPreferencesKey] ?: false
    }

}