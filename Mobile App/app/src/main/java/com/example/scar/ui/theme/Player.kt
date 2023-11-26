package com.example.scar.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(@SerialName(value = "leaderboard_id") val leaderboardID: Int,
                  @SerialName(value = "user_id") val userID: Int)