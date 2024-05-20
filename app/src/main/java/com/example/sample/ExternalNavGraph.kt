package com.example.sample

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController)
{
    NavHost(navController = navController, startDestination =ExternalScreens.SignUpScreen.route)
    {

        composable(route = ExternalScreens.SignUpScreen.route)
        {
            SignUpScreen(navController)
        }
        composable(route = ExternalScreens.LoginScreen.route)
        {
            LoginScreen(navController)
        }
         composable(route = ExternalScreens.MainScreen.route)
         {
             MainScreen(navController)
         }
    }
}