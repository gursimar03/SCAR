package com.jcbottomnavigationdemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.scar.ui.theme.Leaderboard
import com.example.scar.ui.theme.Screen

sealed class NavigationItemBot(var route: String, val icon: ImageVector?, var title: String) {
    object Home : NavigationItemBot(Screen.MainScreen.route, Icons.Rounded.Home, "Home")
    object Crosshair : NavigationItemBot("Crosshair", Icons.Rounded.List, "Crosshair")
    object Stats : NavigationItemBot("Stats", Icons.Rounded.Info, "Stats")
    object Leaderboard : NavigationItemBot(com.example.scar.ui.theme.Leaderboard.World.route, Icons.Rounded.Info, "Leaderboard")

}