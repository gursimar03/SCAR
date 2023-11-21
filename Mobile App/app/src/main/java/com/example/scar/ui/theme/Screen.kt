package com.example.scar.ui.theme

sealed class Screen(val route:String){
    object LogIn : Screen("login_screen")
    object MainScreen : Screen("main_screen")

    fun withArgs(args:String): String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }

}
