@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.scar.ui.theme

import androidx.fragment.app.activityViewModels
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scar.R
import com.example.scar.network.Api
import com.example.scar.screens.LeaderboardViewModel
import kotlinx.serialization.*
import kotlin.math.max
import kotlin.math.min


data class LeaderboardEntry(val name: String, val score: Int)

@Composable
fun leaderboard(modifier: Modifier = Modifier, navController: NavController, region:String?){
    Surface(modifier = Modifier.fillMaxSize(),
        color = mainBg // Set the desired background color here
    ) {
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds,
            modifier=Modifier.fillMaxWidth())

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(70.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        cardBg.copy(alpha = 0.9f)
                    ),

                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.fillMaxSize(),)
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (region != null) {
                    Button(
                        onClick = { navController.navigate(Leaderboard.Regional.route) },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            cardBg.copy(alpha = 0.9f)
                        ),
                        modifier = Modifier
                            .size(200.dp)
                            .padding(15.dp)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .border(
                                width = 3.dp,
                                color = if (region != null) Color.White else Color.Transparent
                            )
                    )
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "Regional",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                                ),
                                fontSize = 20.sp
                            )
                            Text(
                                text = "34",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Bold)
                                ),
                                fontSize = 30.sp
                            )
                        }
                    }
                    Button(
                        onClick = {
                             navController.navigate(Leaderboard.World.route)
                        },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            cardBg.copy(alpha = 0.9f)
                        ),
                        modifier = Modifier
                            .size(200.dp)
                            .padding(15.dp)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .border(
                                width = 3.dp,
                                color = if (region == null) Color.White else Color.Transparent
                            )
                    )
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "Regional",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                                ),
                                fontSize = 20.sp
                            )
                            Text(
                                text = "34",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Bold)
                                ),
                                fontSize = 30.sp
                            )
                        }
                    }
                } else {
                    Button(
                        onClick = { navController.navigate(Leaderboard.Regional.route) },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            cardBg.copy(alpha = 0.9f)
                        ),
                        modifier = Modifier
                            .size(200.dp)
                            .padding(15.dp)
                            .clip(shape = RoundedCornerShape(6.dp))
                    )
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "Regional",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                                ),
                                fontSize = 20.sp
                            )
                            Text(
                                text = "34",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Bold)
                                ),
                                fontSize = 30.sp
                            )
                        }
                    }
                    Button(
                        onClick = {
                             navController.navigate(Leaderboard.World.route)
                        },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            cardBg.copy(alpha = 0.9f)
                        ),
                        modifier = Modifier
                            .size(200.dp)
                            .padding(15.dp)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .border(3.dp, Color.White)
                    )
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "World",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                                ),
                                fontSize = 20.sp
                            )
                            Text(
                                text = "10,325",
                                style = TextStyle(color = Color.White),
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Bold)
                                ),
                                fontSize = 30.sp
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .height(385.dp) // Use weight to make this row take the remaining available space
            ) {
                // Your LeaderboardUI content
                LeaderboardUI()
            }
            Row(
                modifier = Modifier
                    .weight(1f)
            ) {
                // LeaderboardItem content
                LeaderboardItem(entry = LeaderboardEntry("You", 123), isGold = false, 999)
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "LEADERBOARDS",
            style = TextStyle(color= Color.White, fontSize = 20.sp),
            fontFamily= FontFamily(
                Font(R.font.montserrat_regular,weight= FontWeight.Normal)
            )
        )
        Spacer(modifier = Modifier.height(10.dp))


        // Leaderboard entries
        LazyColumn {
            itemsIndexed(leaderboardData.filter {
                it.name.contains(
                    searchText,
                    ignoreCase = true
                )
            }) { index, entry ->
                LeaderboardItem(entry = entry, isGold = index < 3, index)
            }
        }
//        Spacer(modifier = Modifier.height(40.dp))


    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun LeaderboardItem(entry: LeaderboardEntry, isGold: Boolean, index:Int) {
    val alpha = if (isGold) {
        // Assuming you want the alpha to increase for the first three items
        val maxAlpha = 0.9f
        val minAlpha = 0.3f
        val alphaStep = (maxAlpha - minAlpha) / 3
        minAlpha + alphaStep * min(2, max(0, index))
    } else {
        1f // No transparency for non-gold items
    }

    // Apply alpha to gold color
    val backgroundColor = when {
        isGold && index < 3 -> Color(0xFFFFD700).copy(alpha = alpha) // Gold color for first three items
        index == 999 -> Color.Blue // Dark blue for index 999
        else -> userBg // Your default background color
    }  // Use gold color for the first three items
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(70)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .height(45.dp)
                .shadow(
                    shape = RoundedCornerShape(70),
                    spotColor = Color.White,
                    elevation = 30.dp,
                ),

        ) {
            // Your existing content
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                Text(text = entry.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily= FontFamily(
                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                    )
                )
                Text(text = entry.score.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily= FontFamily(
                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                    )
                )
            }

            // Additional content (you can customize this)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp)
            ) {
            }
        }
    }
}

@Composable
fun LeaderboardUI() {
//    val viewModel:LeaderboardViewModel = viewModel()
//    val vm = viewModel<LeaderboardViewModel>()
    val leaderboardViewModel: LeaderboardViewModel = viewModel()




//    leaderboardViewModel.getUserInfo()
//    Log.d("leaderboarddata",leaderboardViewModel.leaderboardData.toString())
    val jsonArray = createJson();
    for (i in 0 until jsonArray.length()) {
        val item = jsonArray.getJSONObject(i)
        Log.d("Item", item.toString())
//        Log.d("id", item.getInt("user_id").toString())
//        User.fromJsonObject(item)


        // Your code here
    }
//    val peopleList = Json.decodeFromString<List<com.example.scar.ui.theme.User>>(jsonArray)
//    Log.d("USER", peopleList.toString())
    val leaderboardData = remember {
        listOf(
            LeaderboardEntry("user 1", 500),
            LeaderboardEntry("user 2", 750),
            LeaderboardEntry("user 3", 600),
            LeaderboardEntry("user 4", 900),
            LeaderboardEntry("user 5", 800),
            LeaderboardEntry("user 6", 700),
            LeaderboardEntry("user 7", 550),
        )
    }

    LeaderboardScreen(leaderboardData = leaderboardData)
}

//fun parseJsonArray(jsonArray: JSONArray): List<LeaderboardEntry> {
//    val leaderboardData = mutableListOf<LeaderboardEntry>()
//
//    for (i in 0 until jsonArray.length()) {
//        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
//        val username = jsonObject.getString("username")
//        val score = jsonObject.getInt("score") // Adjust this based on your JSON structure
//
//        val entry = LeaderboardEntry(username, score)
//        leaderboardData.add(entry)
//    }
//
//    return leaderboardData
//}





