package com.example.scar.ui.theme

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

import org.json.JSONObject
@Serializable
data class User(@SerialName(value = "user_id") val userId: Int,
                @SerialName(value = "kills") val kills: Int,
                @SerialName(value = "spots") val spots: Int,
                @SerialName(value = "accuracy") val accuracy: Int,
                @SerialName(value = "travelled") val travelled: Int,
                @SerialName(value = "username") val username: String,





//                @SerialName(value = "username") val username: String,
//                @SerialName(value = "crosshair_id") val crosshairId: Int,
//                @SerialName(value = "stats") val stats: Stats,
//                @SerialName(value = "match_history") val matchHistory: List<Match>,
               )

@Serializable
data class UserTokens(@SerialName(value = "success") val success : Boolean,
                      @SerialName(value = "token") val token : String)


//    @Serializable
//    data class Stats(
//        val kills: Int,
//        val spots: Int,
//        val accuracy: Double,
//        @SerialName(value = "traveled") val travelled: Double
//    )
//    data class Match(
//        @SerialName(value = "match_id") val matchId: Int,
//        @SerialName(value = "arena_id") val arenaId: Any, // Change the type based on your actual data
//        @SerialName(value = "team_id") val teamId: Int,
//        val result: String,
//        @SerialName(value = "team1_score") val team1Score: Int,
//        @SerialName(value = "team2_score") val team2Score: Int,
//        val stats: Stats
//    )
//    companion object {
////        fun fromJsonObject(jsonObject: JSONObject): User {
////            val username = jsonObject.getString("username")
////            val id = jsonObject.getInt("user_id") // Adjust this based on your JSON structure
////            val statsObject = jsonObject.getJSONObject("stats") // Get the "stats" field as a JSON object
////
////            val kills = statsObject.getInt("kills")
////            val spots = statsObject.getInt("spots")
////            val accuracy = statsObject.getDouble("accuracy")
////            val travelled = statsObject.getDouble("travelled")
////            val score = calculateScore(kills, spots, accuracy, travelled)
//////            val score = 1
////          return User(username, id,kills,spots,accuracy,travelled,score)
////        }
//    }




