package com.example.scar.ui.theme

sealed class Screen(val route:String){

	object SplashScreen : Screen("splashScreen")

    object LogIn : Screen("login_screen")
    object MainScreen : Screen("main_screen")

    object StartMatch : Screen("startmatch_screen")

    object LinkWeapon : Screen("linkweapon_screen")

    fun withArgs(args:String): String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }

}
