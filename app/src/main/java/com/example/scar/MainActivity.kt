package com.example.scar

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scar.ui.theme.SCARTheme
import com.example.scar.ui.theme.SCARTheme
import com.example.scar.ui.theme.cardBg
import com.example.scar.ui.theme.mainBg


class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SCARTheme {
                // A surface container using the 'background' color from the theme
                leaderboard()
//
//                val url = "http://192.168.1.5:5000//match/1"  // Replace with your actual URL
//
//                // Create a request queue
//                val requestQueue = Volley.newRequestQueue(this)
//
//                // Create a GET request
//                val jsonObjectRequest = JsonObjectRequest(
//                    Request.Method.GET, url, null,
//                    Response.Listener { response ->
//                        // Handle the JSON response
//                        val message = response.getString("message")
//                        Log.d("JSON","$message")
//                    },
//                    Response.ErrorListener { error ->
//                        // Handle errors here
//                        Log.d("ada:", "Error: ${error.message}")
//                    }
//                )
//
//                // Add the request to the request queue
//                requestQueue.add(jsonObjectRequest)

            }
        }
    }
}
data class LeaderboardEntry(val name: String, val score: Int)

@Composable
fun leaderboard(modifier: Modifier = Modifier){
    Surface(modifier = Modifier.fillMaxSize(),
        color = mainBg// Set the desired background color here
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        // Handle button click for the first button
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        cardBg.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(15.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .border(3.dp, Color.White)
                )
                {
                    Text(
                        text = "Regional",
                        style = TextStyle(color = Color.White)
                    )
                }
                Button(
                    onClick = {
                        // Handle button click for the first button
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        cardBg.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(15.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                )
                {
                    Text(text = "World")
                }
            }
            Row {
                LeaderboardUI()
            }

        }
    }
}

@Composable
fun LeaderboardScreen(leaderboardData: List<LeaderboardEntry>) {
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(cardBg.copy(alpha = 0.6f))

        ,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "LEADERBOARDS",
            style = TextStyle(color= Color.White, fontSize = 20.sp),
        )


        // Leaderboard entries
        LazyColumn {
            itemsIndexed(leaderboardData.filter {
                it.name.contains(
                    searchText,
                    ignoreCase = true
                )
            }) { index, entry ->
                LeaderboardItem(entry = entry, isGold = index < 3)
            }
        }
        Spacer(modifier = Modifier.height(90.dp))
        Row(){
            LeaderboardItem(entry = LeaderboardEntry("player",123), isGold = false)

        }

    }


}

@SuppressLint("SuspiciousIndentation")
@Composable
fun LeaderboardItem(entry: LeaderboardEntry, isGold: Boolean) {
    val backgroundColor = if (isGold) Color(0xFFFFD700) else Color.White  // Use gold color for the first three items
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = entry.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = entry.score.toString(), style = MaterialTheme.typography.bodyMedium)
        }

    }

}

@Composable
fun LeaderboardUI() {
    val leaderboardData = remember {
        listOf(
            LeaderboardEntry("User 1", 500),
            LeaderboardEntry("User 2", 750),
            LeaderboardEntry("User 3", 600),
            LeaderboardEntry("User 4", 900),
            LeaderboardEntry("User 5", 800),
            LeaderboardEntry("User 6", 700),
            LeaderboardEntry("User 7", 550),
        )
    }

    LeaderboardScreen(leaderboardData = leaderboardData)
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode")
@Composable
fun GreetingPreview() {
    leaderboard()
}