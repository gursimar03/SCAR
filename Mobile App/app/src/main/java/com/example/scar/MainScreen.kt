@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.scar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scar.interfaces.MainMenu
import com.example.scar.ui.theme.Leaderboard
import com.example.scar.ui.theme.Screen
import com.example.scar.interfaces.leaderboard
//import com.example.scar.ui.theme.login
import com.whitebatcodes.myloginapplication.interfaces.LoginForm


//enum class LeaderboardScreen() {
//    Regional,
//    World
//}

@Composable
fun Navigation()
{
    val navController  = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route)
    {
        composable(route = Leaderboard.World.route)
        {
            leaderboard(region = null,navController = navController)
        }
        composable(route = Leaderboard.Regional.route){
            leaderboard(navController = navController,region="ireland")
        }
        composable(route = Screen.LogIn.route){
//            leaderboard(navController = navController,region="ireland")
            LoginForm()
        }
        composable(route = Screen.MainScreen.route){
//            leaderboard(navController = navController,region="ireland")
            MainMenu()
        }

    }

}


//@Composable
//fun MainScreen(navController: NavController) {
//    var text by remember {
//        mutableStateOf(" ")
//    }
//    Column(verticalArrangement = Arrangement.Center,
//    modifier= Modifier
//        .fillMaxWidth()
//        .padding(horizontal = 50.dp)) {
//        TextField(
//            value = text,
//            onValueChange ={
//                text = it
//        },
//            modifier = Modifier.fillMaxWidth())
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = {navController.navigate(Screen.DetailScreen.withArgs(text)) },
//            modifier= Modifier.align(Alignment.End))
//            {
//            Text(text = "to DetailScreen")
//        }
//
//    }
//}
//
//@Composable
//fun DetailScreen(name:String?)
//{
//    Box(contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()){
//        Text(text="Hello, $name")
//    }
//}