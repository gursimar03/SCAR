package com.example.scar.interfaces

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scar.R
import com.example.scar.screens.PlayerUiState
import com.example.scar.screens.PlayerViewModel
import com.example.scar.ui.theme.Global
import com.example.scar.ui.theme.LeaderboardData
import com.example.scar.ui.theme.Player
import com.example.scar.ui.theme.Screen
import com.example.scar.ui.theme.cardBg2
import com.example.scar.ui.theme.cardBgDark
import com.example.scar.ui.theme.creme
import com.example.scar.ui.theme.textBg

//sources
///https://stackoverflow.com/questions/72794300/is-there-a-way-to-filter-the-exposed-dropdown-menu-options-depending-on-the-valu/72795638#72795638
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Startmatch(navController: NavController) {
    var arena by remember { mutableStateOf("Dundalk Arena") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
//                .fillMaxSize()
                .padding(horizontal = 16.dp)

        ) {
            Card(
                modifier = Modifier
//                    .fillMaxSize()
                    .padding(16.dp)
                    .height(500.dp)
                    .shadow(
//                        spotColor = Color.Green,
                        elevation = 8.dp,
                    ),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Text(
                    text = "Configuration",
                    style = TextStyle(color= Color.Black, fontSize = 30.sp),
                    fontFamily= FontFamily(
                        Font(R.font.montserrat_bold,weight= FontWeight.Normal)
                    ),
                    modifier = Modifier.padding(10.dp)

                )
                Column(
                    modifier = Modifier
//                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                        ,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .padding(0.dp)
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Black,
                                elevation = 30.dp,
                            )
                            ) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = cardBgDark),
                                modifier = Modifier.width(130.dp)){
                                Text(text = "Arena",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

//                            SwitchMinimalExample()
                            TextField(
                                value = arena,
                                onValueChange = { arena = it },

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                        ,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Black,
                                elevation = 30.dp,
                            )) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = cardBgDark),
                                modifier = Modifier.width(130.dp)){
                                Text(text = "GPS",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

                            Spacer(modifier = Modifier.width(80.dp))
                            SwitchMinimalExample("GPS")
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                        ,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Black,
                                elevation = 30.dp,
                            )) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = cardBgDark),
                                modifier = Modifier.width(130.dp)){
                                Text(text = "Highlight",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

                            Spacer(modifier = Modifier.width(80.dp))
                            SwitchMinimalExample("highlight")
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    ElevatedCard(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Black,
                                elevation = 30.dp,
                            ),
                        ) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = cardBgDark),
                                modifier = Modifier.width(130.dp)){
                                Text(text = "LED",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

                            Spacer(modifier = Modifier.width(80.dp))
                            SwitchMinimalExample("LED")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            val playerViewModel: PlayerViewModel = viewModel()
            Button(
                modifier = Modifier
                    .width(350.dp)
                    .height(80.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(70),
                        clip = true
                    ),
                onClick = {
                    TestUI(playerUiState = playerViewModel.playerUiState
                    )
                    Api.sendConfig();
                    navController.navigate(Screen.Stop.route)

                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    textBg
                ),
//                border = BorderStroke(5.dp, Color.Black)

            ) {
                Text(text = "Done", fontFamily= FontFamily(
                    Font(R.font.montserrat_bold,weight= FontWeight.Normal)
                ), fontSize = 20.sp)
            }

        }

    }
    Spacer(modifier = Modifier.height(40.dp))

    BottomNavigationBar(navController = navController)
}



@Composable
fun SwitchMinimalExample(variable:String) {
    var checked by remember { mutableStateOf(true) }
    if (checked == true){
        if(variable == "GPS")
        {
            Global.GPS = 1
        }
        else if(variable == "highlight")
        {
            Global.highlight = 1
        }
        else if(variable == "LED")
        {
            Global.LED = 1
        }
    }
    else if (checked == false){
        if(variable == "GPS")
        {
            Global.GPS = 0
        }
        else if(variable == "highlight")
        {
            Global.highlight = 0
        }
        else if(variable == "LED")
        {
            Global.LED = 0
        }
    }
    Log.d("Config","GPS: " + Global.GPS.toString() + " highlight:" + Global.highlight.toString() + " LED:" + Global.LED.toString() )

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        modifier = Modifier
            .width(150.dp)
            .padding(15.dp)
    )
}


fun TestUI(playerUiState: PlayerUiState) {

    when (playerUiState) {
        is PlayerUiState.Loading -> Loading2()
        is PlayerUiState.Success->
            Succeed(playerUiState.users)


        is PlayerUiState.Error -> Loading2()
        else -> {
            Loading2()
        }
    }
}

fun Succeed(testList: Data){
    Log.d("Success",testList.toString())
    val players: List<Player> = testList.players
    Log.d("Players",players.toString())

    // Print the player infor  mation
//    players.forEach { player ->
//        println("Leaderboard ID: ${player.leaderboardID}, User ID: ${player.userID}")
//    }

}
fun Loading2() {
//    val successViewModel: SuccessViewModel = viewModel()
    Log.d("NoJson", "failed")
}
