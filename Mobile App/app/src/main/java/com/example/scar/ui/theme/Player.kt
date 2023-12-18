package com.example.scar.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "players")
@Serializable
data class Player(
//                @SerialName(value = "leaderboard_id") val leaderboardID: Int,
                  @SerialName(value = "user_id") @PrimaryKey val userID: Int,
                  @SerialName(value = "user_name") @PrimaryKey val username: String,
                  @SerialName(value = "score") @PrimaryKey val score: Int,
    )