package com.example.scar.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scar.ui.theme.cardBg2
import com.jcbottomnavigationdemo.navigation.NavigationItemBot
import androidx.navigation.NavHostController as NavHostController1

@Composable
fun MainMenu() {
    Surface {
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
                Column(
                    modifier = Modifier
//                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .padding(40.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(modifier = Modifier.size(50.dp), text = "KILLS:     23")
                            Text(text = "KILLS:     23")
                            Text(text = "KILLS:     23")
                            Text(text = "KILLS:     23")
                            Text(text = "KILLS:     23")
                        }
                    }

                    Button(modifier = Modifier.size(200.dp),onClick = { /*TODO*/ }) {
                        Text(text = "Start Game")
                    }

                }
            }
            bottomTab(navController = rememberNavController())

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomTab(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNavigationBar(navController = navController)
            }
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {}) {
//                Icon(Icons.Filled.Add, "Add")
//            }
//        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
//            Navigations(navController = navController)
        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItemBot.Home,
        NavigationItemBot.Crosshair,
        NavigationItemBot.Stats,
        NavigationItemBot.Settings
    )
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItemBot.Home.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon!!, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}