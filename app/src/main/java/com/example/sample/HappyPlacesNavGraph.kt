package com.example.sample

import Archive
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.happyPlacesNavGraph(navController: NavHostController)
{
    navigation(route = Graph.HAPPY, startDestination = HappyScreen.HappyHomeScreen.route)
    {
        composable(route = HappyScreen.HappyHomeScreen.route)
        {
                HappyPlacesHome(navController)
        }
        composable(route  = HappyScreen.ArchiveScreen.route)
        {
            Archive(navController = navController)
        }
    }
}

sealed class HappyScreen(val route : String)
{
    object HappyHomeScreen : HappyScreen(route = "happy_places_home_screen")
    object ArchiveScreen : HappyScreen(route = "happy_places_archive_screen")

}




