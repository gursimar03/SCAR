package com.example.scar.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardData(@SerialName(value = "data")
                 var players: List<Player>,
                           val success: Boolean)

