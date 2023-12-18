package com.example.scar.ui.theme


import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Arena(
//                        @SerialName(value = "arena_id") val leaderboardID: Int,
    @SerialName(value = "arena_id")  val arenaID: Int,
    @SerialName(value = "arena_name")  val arena_name: String,
    @SerialName(value = "geofence")  val fence: Geofence,
    )

