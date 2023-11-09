@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.scar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.scar.ui.theme.Leaderboard
import com.example.scar.ui.theme.Screen
import com.example.scar.ui.theme.cardBg
import com.example.scar.ui.theme.leaderboard
import com.example.scar.ui.theme.mainBg




enum class LeaderboardScreen() {
    Regional,
    World
}

@Composable
fun Navigation()
{
    val navController  = rememberNavController()
    NavHost(navController = navController, startDestination = Leaderboard.World.route)
    {
        composable(route = Leaderboard.World.route)
        {
            leaderboard(navController = navController)
        }
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
    }

}


@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf(" ")
    }
    Column(verticalArrangement = Arrangement.Center,
    modifier= Modifier
        .fillMaxWidth()
        .padding(horizontal = 50.dp)) {
        TextField(
            value = text,
            onValueChange ={
                text = it
        },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {navController.navigate(Screen.DetailScreen.withArgs(text)) },
            modifier= Modifier.align(Alignment.End))
            {
            Text(text = "to DetailScreen")
        }

    }
}

@Composable
fun DetailScreen(name:String?)
{
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        Text(text="Hello, $name")
    }
}