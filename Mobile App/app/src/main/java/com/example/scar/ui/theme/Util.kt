package com.example.scar.ui.theme

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

////placeholder for route request
fun createJson(): JSONArray {
    val jsonArray = JSONArray()
    try {
        // Create an array to hold user objects


        // com.example.scar.ui.theme.User 1
        val user1 = JSONObject()
        user1.put("user_id", 1)
        user1.put("username", "TheOne")
        user1.put("kills", 20)
        user1.put("spots", 40)
        user1.put("accuracy", 0.5)
        user1.put("traveled", 3200)
        jsonArray.put(user1)

        // com.example.scar.ui.theme.User 2
        val user2 = JSONObject()
        user2.put("user_id", 2)
        user2.put("username", "Matt")
        user2.put("kills", 10)
        user2.put("spots", 20)
        user2.put("accuracy", 0.5)
        user2.put("traveled", 1200)
        jsonArray.put(user2)

        // com.example.scar.ui.theme.User 3
        val user3 = JSONObject()
        user3.put("user_id", 3)
        user3.put("username", "JeremyXO")
        user3.put("kills", 5)
        user3.put("spots", 10)
        user3.put("accuracy", 0.5)
        user3.put("traveled", 2800)
        jsonArray.put(user3)

        // com.example.scar.ui.theme.User 4
        val user4 = JSONObject()
        user4.put("user_id", 4)
        user4.put("username", "BigGlockEnergy")
        user4.put("kills", 7)
        user4.put("spots", 10)
        user4.put("accuracy", 0.7)
        user4.put("traveled", 8000)
        jsonArray.put(user4)

        // com.example.scar.ui.theme.User 5
        val user5 = JSONObject()
        user5.put("user_id", 5)
        user5.put("username", "kennyS")
        user5.put("kills", 10)
        user5.put("spots", 10)
        user5.put("accuracy", 1)
        user5.put("traveled", 10)
        jsonArray.put(user5)

        // Convert the array to a JSON string
        val jsonString = jsonArray.toString()


        // Print the JSON string
        Log.d("JSON: ",jsonString)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return jsonArray
}

 fun calculateScore(kills: Int, spots: Int, accuracy: Int, travelled: Int): Int {
    // Your scoring logic here (adjust as needed)
    val killsWeight = 20
    val spotsWeight = 10
    val accuracyWeight = 30
    val travelledWeight = 0.5

    return (kills * killsWeight + spots * spotsWeight + (accuracy * 100).toInt() * accuracyWeight - (travelled / 100).toInt() * travelledWeight).toInt()
}