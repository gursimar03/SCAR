package com.example.scar.interfaces

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scar.R
import com.example.scar.network.Api
import com.example.scar.screens.MatchHistoryUiState
import com.example.scar.screens.MatchHistoryViewModel
import com.example.scar.screens.PlayerUiState
import com.example.scar.screens.PlayerViewModel
import com.example.scar.ui.theme.Leaderboard
import com.example.scar.ui.theme.LeaderboardData
import com.example.scar.ui.theme.MatchData
import com.example.scar.ui.theme.MatchHistory
import com.example.scar.ui.theme.Screen
import com.example.scar.ui.theme.cardBg2
import com.example.scar.ui.theme.cardBgDark
import com.example.scar.ui.theme.creme
import com.example.scar.ui.theme.textBg
import com.jcbottomnavigationdemo.navigation.NavigationItemBot

@Composable
fun MainMenu(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(),
//        color = cardBgDark,

        ){
//        Image(painter = painterResource(id = R.drawable.background),
//            contentDescription = "bg",
//            contentScale = ContentScale.FillBounds,
//            modifier=Modifier.fillMaxWidth())
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
//                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Card(
                modifier = Modifier
                    .height(550.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ){
                val matchHistoryModel: MatchHistoryViewModel = viewModel()
                MatchHistoryUI(matchHistoryModel.matchHistoryUiState, navController);
            }
            Button(
                modifier = Modifier
                    .width(350.dp)
                    .height(80.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(70),
                        clip = true
                    ),
//                    .clip(RoundedCornerShape(8.dp)),

                onClick = { navController.navigate(Screen.LinkWeapon.route)
//                    Api.setupPubnub()
                          },
                colors = ButtonDefaults.buttonColors(
                    cardBg2
//                    Color.White
                )
            ) {
                Text(
                    text = "Start Match",
                    fontFamily = FontFamily(Font(R.font.montserrat_bold, weight = FontWeight.Normal)),
                    fontSize = 20.sp
                )
            }

        }

    }
    BottomNavigationBar(navController = navController)

}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItemBot.Home,
//        NavigationItemBot.Crosshair,
//        NavigationItemBot.Stats,
        NavigationItemBot.Leaderboard
    )
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItemBot.Home.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
        containerColor = Color.White
        // Replace with your desired background
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        item.icon!!,
                        contentDescription = item.title,
                        modifier = Modifier.padding(0.dp) // This can help to eliminate extra padding around the icon
                    )
                },
                label = {
                    Text(
                        item.title,
                        modifier = Modifier.padding(0.dp) // This can help to eliminate extra padding around the label
                    )
                },
//                { navController.navigate(Leaderboard.Regional.route) }
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    Log.d("currentRoute",currentRoute)
                    navController.navigate(item.route)
//                    {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                },
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .zIndex(1f)
                    .background(
                        Color.White.copy(alpha = 0.8f)
                    )// This can help to eliminate extra padding around the entire item
            )
        }
    }
}



@Composable
fun matches(Kills:Int, Spotted:Int, Travelled:Int, Date:String, navController: NavController, matchID:Int) {
    Card(
        modifier = Modifier
//                    .fillMaxSize()
            .padding(16.dp)
            .height(550.dp)
            .shadow(
//                        spotColor = Color.Green,
                elevation = 8.dp,
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {    Text(
        text = Date,
        style = TextStyle(color = Color.Black, fontSize = 30.sp),
        fontFamily = FontFamily(
            Font(R.font.montserrat_bold, weight = FontWeight.Normal)
        ),
        modifier = Modifier.padding(10.dp)

    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = cardBgDark.copy(alpha = 0.8f))

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Won",
                    style = TextStyle(color = Color.Green),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_bold, weight = FontWeight.Normal)
                    ),
                    modifier = Modifier.padding(5.dp)
                )


                Spacer(modifier = Modifier.height(15.dp))

                ElevatedCard(
                    colors = CardDefaults.cardColors(containerColor = cardBgDark),
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth()
                        .shadow(
                            shape = RoundedCornerShape(70),
                            spotColor = Color.White,
                            elevation = 30.dp,
                        ),
                ) {
                    Row {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = cardBgDark),
                            modifier = Modifier.width(130.dp)
                        ) {
                            Text(
                                text = "Kills:",
                                style = TextStyle(color = Color.White),
                                fontSize = 20.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                                ),
                                modifier = Modifier.padding(15.dp)
                            )
                        }

//                                    Spacer(modifier = Modifier.width(30.dp))
                        Text(
                            text = Kills.toString(),
                            style = TextStyle(color = Color.White),
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                            ),
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(35.dp))

                ElevatedCard(
                    colors = CardDefaults.cardColors(containerColor = cardBgDark),
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth()
                        .shadow(
                            shape = RoundedCornerShape(70),
                            spotColor = Color.White,
                            elevation = 30.dp,
                        ),
                ) {
                    Row {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = cardBgDark),
                            modifier = Modifier.width(130.dp)
                        ) {
                            Text(
                                text = "Spotted:",
                                style = TextStyle(color = Color.White),
                                fontSize = 20.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                                ),
                                modifier = Modifier.padding(15.dp)
                            )
                        }

//                                    Spacer(modifier = Modifier.width(60.dp))
                        Text(
                            text = Spotted.toString(),
                            style = TextStyle(color = Color.White),
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                            ),
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(35.dp))

                ElevatedCard(
                    colors = CardDefaults.cardColors(containerColor = cardBgDark),
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth()
                        .shadow(
                            shape = RoundedCornerShape(70),
                            spotColor = Color.White,
                            elevation = 30.dp,
                        ),
                ) {
                    Row {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = cardBgDark),
                            modifier = Modifier.width(130.dp)
                        ) {
                            Text(
                                text = "Travelled:",
                                style = TextStyle(color = Color.White),
                                fontSize = 20.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                                ),
                                modifier = Modifier.padding(15.dp)
                            )
                        }

//                                    Spacer(modifier = Modifier.width(70.dp))
                        Text(
                            text = Travelled.toString() + "KM",
                            style = TextStyle(color = Color.White),
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_regular, weight = FontWeight.Normal)
                            ),
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                }
                                            Spacer(modifier = Modifier.height(35.dp))
                            Log.d("ID", matchID.toString())
                            Button(onClick = {
                                navController.navigate(Screen.MatchDetail.withArgs(matchID.toString()))
                            }) {
                                Icon( Icons.Rounded.KeyboardArrowDown, contentDescription ="Show More" )

                            }
            }

        }
    }
}
}
@Composable
fun MatchHistoryUI(matchHistory: MatchHistoryUiState, navController: NavController) {

    when (matchHistory) {
        is MatchHistoryUiState.Loading -> LoadingMatches(navController)
        is MatchHistoryUiState.Success ->
            SuccessLoadingMatches(matchHistory.data, navController)
//

        is MatchHistoryUiState.Error -> LoadingMatches(navController)
        else -> {
            LoadingMatches(navController)}
    }
}

data class matchData(val kills: Int, val spotted : Int, val travelled : Int, val date:String, val matchID: Int)
@Composable
fun LoadingMatches(navController: NavController)
{
    val matchDataExample = remember {
        listOf(
            matchData(100,25,9000, "2023-11-21",1),
            matchData(5,1000,200000, "2023-11-21",2),
            matchData(3,123,123, "2023-11-21",3),

            )
    }

    LazyColumn {
        itemsIndexed(matchDataExample) { index, entry ->
            matches(entry.kills,entry.spotted,entry.travelled,entry.date,navController,entry.matchID)
        }
    }
}

@Composable
fun SuccessLoadingMatches(matchInfoList: MatchData, navController: NavController)
{
    Log.d("userInfoList",matchInfoList.matches.toString())


    val matchEntryList:List<matchData> = matchInfoList.matches.map {
        matchData(it.kills,it.spotted,it.travelled,it.date,it.matchID)
    }
    val matchHistoryData = remember {
        matchEntryList
    }

    Log.d("leaderEntryList",matchEntryList.toString())

    LazyColumn {
        itemsIndexed(matchHistoryData) { index, entry ->
            matches(entry.kills,entry.spotted,entry.travelled,entry.date,navController,entry.matchID)
        }
    }
}



