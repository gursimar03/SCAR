package com.example.scar.ui.theme

sealed class Leaderboard(val route:String){
    object Regional : Leaderboard("regional_leaderboard")
    object World : Leaderboard("world_leaderboard")
}
