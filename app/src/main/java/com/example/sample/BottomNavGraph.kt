package com.example.sample



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun BottomNavGraph(navController: NavHostController,parentNavController: NavHostController) {
    NavHost(
        route = Graph.ROOT,
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Home(parentNavController)
        }

        happyPlacesNavGraph(navController)

        fitnessNavGraph(navController)
        composable(route = BottomBarScreen.Chatbot.route) {
          Chatbot()
        }
    }
}

object Graph{
    const val ROOT = "Root"
    const val HAPPY = "happy_places_graph"
    const val FITNESS = "fitness_graph"
}