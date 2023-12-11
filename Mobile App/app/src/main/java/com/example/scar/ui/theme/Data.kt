package com.example.scar.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.json.JSONArray

@Serializable
data class Data(@SerialName(value = "data")
                 var players: List<Player>,
                val success: Boolean)

