package com.example.sample
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun Fitness(navController: NavHostController)
{
    Box(modifier = Modifier
        .background(color = Color.Cyan)
        .fillMaxSize()) {

        Button(onClick = {
            navController.navigate(FitnessScreen.CategoryScreen.route)

        }) {
            Text(text = "START")

        }
    }

}