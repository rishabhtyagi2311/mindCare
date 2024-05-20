package com.example.sample
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Bolt

import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {


    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object  Chatbot : BottomBarScreen(
        route = "chat bot",
        title = "Chat bot",
        icon = Icons.Default.Person
    )

    object Archive : BottomBarScreen(
        route = "happy_places_home_screen",
        title = "Archive",
        icon = Icons.Default.Archive

    )
    object Fitness : BottomBarScreen(
        route = "fitness_home_screen",
        title = "Fitness",
        icon = Icons.Default.Bolt
    )
}