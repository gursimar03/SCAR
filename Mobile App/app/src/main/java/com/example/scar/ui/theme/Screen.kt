package com.example.scar.ui.theme

sealed class Screen(val route:String){

	object SplashScreen : Screen("splashScreen")

    object LogIn : Screen("login_screen")
    object MainScreen : Screen("main_screen")

    object StartMatch : Screen("startmatch_screen")

    object LinkWeapon : Screen("linkweapon_screen")

    object MatchDetail : Screen("matchdetail_screen")

    fun withArgs(args: String): String {
        return buildString {
            append(route) // Assuming route is declared elsewhere
            append("/$args") // Append args directly without iterating over each character
        }
    }


}
