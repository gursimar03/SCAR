package com.example.scar.ui.theme

sealed class Screen(val route:String){
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(args:String): String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }

}
