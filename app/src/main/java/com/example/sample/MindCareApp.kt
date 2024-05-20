package com.example.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MindCareApp(navController: NavHostController)
{
    Surface (modifier  = Modifier.fillMaxSize().padding(top = 50.dp, bottom = 45.dp),
        color = Color.White){

        SetupNavGraph(navController = navController)
    }
}
