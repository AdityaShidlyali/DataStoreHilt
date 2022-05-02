package com.adityashidlyali.datastorehilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStorePreferenceManager: DataStorePreferenceManager
) : ViewModel() {

    private var onBoardStatus: Boolean = false

    fun setOnBoardStatus(onBoardStatusBoolean: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePreferenceManager.setOnBoardStatus(onBoardStatusBoolean)
        }
    }

    fun getOnBoardStatus(): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePreferenceManager.onBoardStatusFlow.collect {
                onBoardStatus = it
            }
        }

        return onBoardStatus
    }

}