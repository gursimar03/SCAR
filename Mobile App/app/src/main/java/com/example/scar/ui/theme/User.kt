import org.json.JSONArray
import org.json.JSONObject

data class User(val username: String, val id: Int,val kills : Int, val spots: Int, val accuracy: Double, val travelled : Double, val score:Int ) {
    companion object {
        fun fromJsonObject(jsonObject: JSONObject): User {
            val username = jsonObject.getString("username")
            val id = jsonObject.getInt("user_id") // Adjust this based on your JSON structure
            val kills = jsonObject.getInt("kills")
            val spots = jsonObject.getInt("spots")
            val accuracy = jsonObject.getDouble("accuracy")
            val travelled = jsonObject.getDouble("travelled")
            val score = calculateScore(kills, spots, accuracy, travelled)
          return User(username, id,kills,spots,accuracy,travelled,score)
        }
    }
}

private fun calculateScore(kills: Int, spots: Int, accuracy: Double, travelled: Double): Int {
    // Your scoring logic here (adjust as needed)
    val killsWeight = 20
    val spotsWeight = 10
    val accuracyWeight = 30
    val travelledWeight = 0.5

    return (kills * killsWeight + spots * spotsWeight + (accuracy * 100).toInt() * accuracyWeight - (travelled / 100).toInt() * travelledWeight).toInt()
}

