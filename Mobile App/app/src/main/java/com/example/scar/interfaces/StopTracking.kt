package com.example.scar.interfaces

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.model.content.CircleShape
import com.example.scar.R
import com.example.scar.network.Api
import com.example.scar.ui.theme.Global

import com.example.scar.ui.theme.Screen
import com.example.scar.ui.theme.textBg

@Composable
fun StopTracking(navController: NavController)
{
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                Global.LED = 0;
                Global.highlight = 0;
                Api.sendConfig();
                navController.navigate(Screen.MainScreen.route)
                      },
            modifier = Modifier
                .padding(50.dp)
                .fillMaxWidth()
                .size(width = 80.dp, height = 250.dp)
                .align(Alignment.Center)
            ,
            colors = ButtonDefaults . buttonColors (
                    Color.Red
                    ),


        ) {
            Text(text = "Stop Tracking",
                fontFamily= FontFamily(
                    Font(R.font.montserrat_bold,weight= FontWeight.Normal)
                ), fontSize = 20.sp)
        }
    }
}