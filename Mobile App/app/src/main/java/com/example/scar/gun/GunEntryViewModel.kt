///*
// * Copyright (C) 2023 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.example.scar.gun
//
//import android.util.Log
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.example.scar.data.GunRepository
//import com.example.scar.ui.theme.Gun
//
///**
// * ViewModel to validate and insert items in the Room database.
// */
//class GunEntryViewModel(private val gunRepository: GunRepository) : ViewModel() {
//
//    /**
//     * Holds current item ui state
//     */
//    var gunUiState by mutableStateOf(GunUiState())
//        private set
//
//    /**
//     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
//     * a validation for input values.
//     */
//    fun updateUiState(itemDetails: GunDetails) {
//        gunUiState =
//            GunUiState(gunDetails = itemDetails, isEntryValid = validateInput(itemDetails))
//    }
//
//    suspend fun saveItem(gun:Gun) {
//
////            gunRepository.insertGun(gunUiState.gunDetails.toItem())
//            gunRepository.insertGun(gun);
//
//          /*  Log.d("error","notinserted");*/
//
//    }
//
//    private fun validateInput(uiState: GunDetails = gunUiState.gunDetails): Boolean {
//        return with(uiState) {
//            name.isNotBlank()
//        }
//    }
//}
//
///**
// * Represents Ui State for an Item.
// */
//data class GunUiState(
//    val gunDetails: GunDetails = GunDetails(),
//    val isEntryValid: Boolean = false
//)
//
//data class GunDetails(
//    val id: Int = 0,
//    val name: String = ""
//)
//
///**
// * Extension function to convert [ItemDetails] to [Item]. If the value of [ItemDetails.price] is
// * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
// * [ItemDetails.quantity] is not a valid [Int], then the quantity will be set to 0
// */
//fun GunDetails.toItem(): Gun = Gun(
//    gunID = id,
//    name = name,
//
//)
//
//
//
///**
// * Extension function to convert [Item] to [ItemUiState]
// */
//fun Gun.toItemUiState(isEntryValid: Boolean = false): GunUiState = GunUiState(
//    gunDetails = this.toItemDetails(),
//    isEntryValid = isEntryValid
//)
//
///**
// * Extension function to convert [Item] to [ItemDetails]
// */
//fun Gun.toItemDetails(): GunDetails = GunDetails(
//    id = gunID,
//    name = name,
//
//)
