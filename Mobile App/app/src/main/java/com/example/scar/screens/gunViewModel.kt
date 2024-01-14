/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.scar.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.marsphotos.model.MarsPhoto
//import com.example.marsphotos.network.MarsApi
import com.example.scar.network.Api
import com.example.scar.ui.theme.GunData
import com.example.scar.ui.theme.LeaderboardData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface GunUiState {
    data class Success(val data: GunData) : GunUiState
    object Error : GunUiState
    object Loading : GunUiState
}

class GunViewModel() : ViewModel() {
    /** The mutable State that stores the status of the most recent request */

    //        private set
    var gunUiState: GunUiState by mutableStateOf(GunUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getGunInfo()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getGunInfo() {
        viewModelScope.launch {
            gunUiState = GunUiState.Loading
            gunUiState= try {
                val newData = Api.retrofitService.getGuns()
                GunUiState.Success(
                    newData
                )
            } catch (e: IOException) {
                GunUiState.Error
            } catch (e: HttpException) {
                GunUiState.Error

            }
        }
    }
}
