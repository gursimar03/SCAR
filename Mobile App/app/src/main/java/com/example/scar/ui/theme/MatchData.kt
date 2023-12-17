package com.example.scar.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchData(@SerialName(value = "data")
                           var matches: List<MatchHistory>,
                           val success: Boolean)