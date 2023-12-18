package com.example.scar.interfaces

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scar.R
import com.example.scar.network.Api
import com.example.scar.screens.MatchHistoryUiState
import com.example.scar.screens.MatchHistoryViewModel
import com.example.scar.screens.PlayerViewModel
import com.example.scar.ui.theme.Arena
import com.example.scar.ui.theme.MatchData
import com.example.scar.ui.theme.Screen
import com.example.scar.ui.theme.cardBg
import com.example.scar.ui.theme.cardBgDark
import com.example.scar.ui.theme.textBg

@Composable
fun MatchDetails(id:Int?, navController: NavController) {
    val matchHistoryModel: MatchHistoryViewModel = viewModel()

    id?.let { MatchDetailsUI(matchHistory = matchHistoryModel.matchHistoryUiState, targetID = it) }

    Button(
        onClick = { navController.navigate(Screen.MainScreen.route) },
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
//    BottomNavigationBar(navController = navController)
}

@Composable
fun MatchDetailsUI(matchHistory: MatchHistoryUiState,targetID : Int) {

    when (matchHistory) {
        is MatchHistoryUiState.Loading -> LoadingMatchDetails()
        is MatchHistoryUiState.Success ->
            SuccessLoadingMatchDetails(matchHistory.data,targetID)
//

        is MatchHistoryUiState.Error -> LoadingMatchDetails()
        else -> {
            LoadingMatchDetails()}
    }
}
data class matchDetailsData(val kills: Int,
                            val spotted : Int,
                            val travelled : Int,
                            val date:String,
                            val matchID: Int,
                            val arena: Arena,
                            val score: Int)
@Composable
fun LoadingMatchDetails()
{
//    val matchDataExample = remember {
//        listOf(
//            matchDetailsData(100,25,9000, "2023-11-21",1),
//            matchDetailsData(5,1000,200000, "2023-11-21",2),
//            matchDetailsData(3,123,123, "2023-11-21",3),
//
//            )
//    }
//
//    LazyColumn {
//        itemsIndexed(matchDataExample) { index, entry ->
//            matches(entry.kills,entry.spotted,entry.travelled,entry.date,entry.matchID)
//        }
//    }
}

@Composable
fun SuccessLoadingMatchDetails(matchInfoList: MatchData,  targetID: Int)
{
    Log.d("userInfoList",matchInfoList.matches.toString())

//    val matchEntryList:List<matchData> = matchInfoList.matches.map {
//        matchData(it.kills,it.spotted,it.score,it.date,it.matchID)
//    }
    val matchEntry: matchDetailsData? = matchInfoList.matches
        .firstOrNull { it.matchID == targetID }
        ?.let {
            matchDetailsData(
                kills = it.kills,
                spotted = it.spotted,
                travelled = it.travelled,
                date = it.date,
                matchID = it.matchID,
                arena = it.arena,
                score = it.score
            )
        }

//    val matchDetailsData = remember {
//
//    }

    Log.d("Match Details",matchEntry.toString())

    DisplayDetails(matchEntry)

}



@Composable
fun DisplayDetails(matchEntryList: matchDetailsData?) {
    Surface(modifier = Modifier.fillMaxSize()

    ) {
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
                    text = "Details",
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

//                            Spacer(modifier = Modifier.width(80.dp))
                            if (matchEntryList != null) {
                                Text(text = matchEntryList.arena.arena_name,
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 18.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }
//                            SwitchMinimalExample()
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
                                Text(text = "Kills",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

//                            Spacer(modifier = Modifier.width(80.dp))
                            if (matchEntryList != null) {
                                Text(text = matchEntryList.kills.toString(),
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 18.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }
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
                                Text(text = "Spotted",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

//                            Spacer(modifier = Modifier.width(80.dp))
                            if (matchEntryList != null) {
                                Text(text = matchEntryList.spotted.toString(),
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 18.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }
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
                                Text(text = "Travelled",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

//                            Spacer(modifier = Modifier.width(80.dp))
                            if (matchEntryList != null) {
                                Text(text = matchEntryList.travelled.toString(),
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 18.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }
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
                                Text(text = "Score",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

//                            Spacer(modifier = Modifier.width(80.dp))
                            if (matchEntryList != null) {
                                Text(text = matchEntryList.score.toString(),
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 18.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }
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
                                Text(text = "Score",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

//                            Spacer(modifier = Modifier.width(80.dp))
                            if (matchEntryList != null) {
                                Text(text = matchEntryList.score.toString(),
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 18.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

//            val playerViewModel: PlayerViewModel = viewModel()


        }

    }
//    Spacer(modifier = Modifier.height(40.dp))
}