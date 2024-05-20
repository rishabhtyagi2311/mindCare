package com.example.sample

sealed class ExternalScreens(val route : String) {

    object MainScreen: ExternalScreens("main_screen")
    object LoginScreen:ExternalScreens("login_screen")
    object SignUpScreen:ExternalScreens("sign_up_screen")

}