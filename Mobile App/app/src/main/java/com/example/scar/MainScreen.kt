@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.scar


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size

//import com.example.scar.ui.theme.login
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.scar.interfaces.LinkWeapon
import com.example.scar.interfaces.MainMenu
import com.example.scar.interfaces.Startmatch
import com.example.scar.interfaces.leaderboard
import com.example.scar.ui.theme.Leaderboard
import com.example.scar.ui.theme.Screen
import com.whitebatcodes.myloginapplication.interfaces.LoginForm
import com.airbnb.lottie.compose.*
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.scar.interfaces.MatchDetails
import kotlinx.coroutines.delay


//enum class LeaderboardScreen() {
//    Regional,
//    World
//}

@Composable
fun SplashScreen(navController: NavController) {
    // Load the Lottie animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scar_loading))

    // Display the Lottie animation
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(200.dp)
        )
    }

    // Navigate to the main screen after a delay
    LaunchedEffect(key1 = true) {
        delay(2000)  // Delay of 2 seconds
        navController.navigate(Screen.MainScreen.route) {
            // Pop up to the splash screen so it won't be available back press
            popUpTo(Screen.SplashScreen.route) { inclusive = true }
        }
    }
}

@Composable
fun Navigation()
{
    val navController  = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route)
    {

		composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Leaderboard.World.route)

        {

            leaderboard(region = null,navController = navController)
        }
        composable(route = Leaderboard.Regional.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }){
            leaderboard(navController = navController,region="ireland")
        }
        composable(route = Screen.LogIn.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }){
            LoginForm(navController = navController)
        }
        composable(route = Screen.MainScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }){
            MainMenu(navController = navController)
        }
        composable(route = Screen.StartMatch.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }){
            Startmatch(navController = navController)
        }

        composable(route = Screen.LinkWeapon.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }){
            LinkWeapon(navController = navController)
        }

        composable(route = Screen.MatchDetail.route + "/{id}",
            arguments = listOf(navArgument("id")
            {
                type = NavType.IntType
                defaultValue = 0;
                nullable = false;
            }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }){entry ->
            MatchDetails(entry.arguments?.getInt("id"), navController = navController)
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