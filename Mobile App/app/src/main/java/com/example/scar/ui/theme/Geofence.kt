package com.example.scar.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geofence(
//                        @SerialName(value = "arena_id") val leaderboardID: Int,
    @SerialName(value = "type")  val type: String,
    @SerialName(value = "coordinates")  val coordinates: List<List<List<Double>>>,
)
