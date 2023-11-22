package com.example.scar.interfaces

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scar.R
import com.example.scar.ui.theme.Leaderboard
import com.example.scar.ui.theme.cardBg2
import com.example.scar.ui.theme.creme
import com.example.scar.ui.theme.textBg
import com.jcbottomnavigationdemo.navigation.NavigationItemBot
import androidx.navigation.NavHostController as NavHostController1

@Composable
fun MainMenu(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds,
            modifier=Modifier.fillMaxWidth())
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
//                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Card(
                modifier = Modifier
//                    .fillMaxSize()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg2)
            ) {
                Text(
                    text = "Last Match",
                    style = TextStyle(color= Color.White, fontSize = 30.sp),
                    fontFamily= FontFamily(
                        Font(R.font.montserrat_bold,weight= FontWeight.Normal)
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
                           .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
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
                                    Card (colors = CardDefaults.cardColors(containerColor = creme),
                                        modifier = Modifier.width(100.dp)){
                                        Text(text = "Kills",
                                            style = TextStyle(color = Color.White),
                                            fontSize = 20.sp,
                                            fontFamily= FontFamily(
                                                Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                            ),
                                            modifier = Modifier.padding(15.dp))
                                    }

                                    Spacer(modifier = Modifier.width(70.dp))
                                    Text(text = "23",
                                        style = TextStyle(color = Color.Black),
                                        fontSize = 20.sp,
                                        fontFamily= FontFamily(
                                            Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                        ),
                                        modifier = Modifier.padding(15.dp))
                                }
                            }

                            Spacer(modifier = Modifier.height(35.dp))

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
                                    Card (colors = CardDefaults.cardColors(containerColor = creme),
                                        modifier = Modifier.width(130.dp)){
                                        Text(text = "Spotted",
                                            style = TextStyle(color = Color.White),
                                            fontSize = 20.sp,
                                            fontFamily= FontFamily(
                                                Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                            ),
                                            modifier = Modifier.padding(15.dp))
                                    }

//                                    Spacer(modifier = Modifier.width(60.dp))
                                    Text(text = "69",
                                        style = TextStyle(color = Color.Black),
                                        fontSize = 20.sp,
                                        fontFamily= FontFamily(
                                            Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                        ),
                                        modifier = Modifier.padding(15.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(35.dp))

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
                                    Card (colors = CardDefaults.cardColors(containerColor = creme),
                                        modifier = Modifier.width(130.dp)){
                                        Text(text = "Travelled",
                                            style = TextStyle(color = Color.White),
                                            fontSize = 20.sp,
                                            fontFamily= FontFamily(
                                                Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                            ),
                                            modifier = Modifier.padding(15.dp))
                                    }

//                                    Spacer(modifier = Modifier.width(70.dp))
                                    Text(text = "12Km",
                                        style = TextStyle(color = Color.Black),
                                        fontSize = 20.sp,
                                        fontFamily= FontFamily(
                                            Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                        ),
                                        modifier = Modifier.padding(15.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(35.dp))

                            Button(onClick = { /*TODO*/ }) {
                                Icon( Icons.Rounded.KeyboardArrowDown, contentDescription ="Show More" )

                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(35.dp))

                    Button(
                        modifier = Modifier.width(300.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            textBg
                        )
                    ) {
                        Text(text = "Start Match", fontFamily= FontFamily(
                            Font(R.font.montserrat_bold,weight= FontWeight.Normal)
                        ), fontSize = 20.sp)
                    }

                }
            }

        }

    }
    BottomNavigationBar(navController = navController)

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun bottomTab(
//    navController: NavHostController
//) {
//    Scaffold(
//        bottomBar = {
//            BottomAppBar(modifier = Modifier) {
//                BottomNavigationBar(navController = navController)
//            }
//        },
////        floatingActionButton = {
////            FloatingActionButton(onClick = {}) {
////                Icon(Icons.Filled.Add, "Add")
////            }
////        }
//    )
//    {
//
//    }
//}
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItemBot.Home,
        NavigationItemBot.Crosshair,
        NavigationItemBot.Stats,
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
            .background(
                Color.Red
            )
            .wrapContentHeight(Alignment.Bottom), // Replace with your desired background
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
                    .zIndex(1f)// This can help to eliminate extra padding around the entire item
            )
        }
    }
}


