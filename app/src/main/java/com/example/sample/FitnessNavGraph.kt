package com.example.sample

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.fitnessNavGraph(navController: NavHostController)
{
    navigation(route = Graph.FITNESS, startDestination = FitnessScreen.FitnessHomeScreen.route)
    {
        composable(route = FitnessScreen.FitnessHomeScreen.route)
        {
            Fitness(navController)
        }
        composable(route = FitnessScreen.CategoryScreen.route)
        {
            CategorySelection(navController = navController)
        }
        composable(route = FitnessScreen.SetTimerScreen.route)
        {
            setTimer(navController = navController)
        }

        composable(route = FitnessScreen.DisplayExercise.route)
        {
            display(navController = navController)
        }

        composable(route = FitnessScreen.BmiCalculator.route)
        {
            BmiCalculator(navController = navController)
        }
        composable(route = FitnessScreen.HistoryScreen.route)
        {
            DisplayHistory()
        }

    }
}

sealed class FitnessScreen(val route : String)
{
    object FitnessHomeScreen : FitnessScreen(route = "fitness_home_screen")
    object CategoryScreen : FitnessScreen(route = "category_home_screen")

    object SetTimerScreen : FitnessScreen(route = "set_timer_screen")

    object DisplayExercise : FitnessScreen(route = "display_exercise_screen")
    object BmiCalculator : FitnessScreen(route = "bmi_calculator_screen")

    object HistoryScreen: FitnessScreen(route = "exercise_history_screen")


}
