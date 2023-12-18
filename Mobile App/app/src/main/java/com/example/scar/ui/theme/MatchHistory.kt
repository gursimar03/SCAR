package com.example.scar.ui.theme

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchHistory(
//                        @SerialName(value = "arena_id") val leaderboardID: Int,
                        @SerialName(value = "enemies_spotted")  val spotted: Int,
                        @SerialName(value = "kills")  val kills: Int,
                        @SerialName(value = "match_id")  val matchID: Int,
                        @SerialName(value = "score")  val score: Int,
                        @SerialName(value = "user_id")  val userID: Int,
                        @SerialName(value = "weapon_id")  val weaponID: Int,
                        @SerialName(value = "match_time")  val matchTime: Int,
                        @SerialName(value = "match_date")  val date: String,
                        @SerialName(value = "arena")  val arena: Arena,
                        @SerialName(value = "travelled")  val travelled: Int)

