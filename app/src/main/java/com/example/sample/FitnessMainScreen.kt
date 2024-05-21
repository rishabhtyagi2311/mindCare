package com.example.sample
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun Fitness(navController: NavHostController)
{
    Box(modifier = Modifier
        .background(color = Color.Cyan)
        .fillMaxSize())
    {
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){

            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(id = R.drawable.fitness_logo),
                contentDescription = "fitness_logo",
                modifier = Modifier
                    .size(400.dp, 400.dp)
                    .padding(5.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)

            {
                androidx.compose.material3.Button(onClick = {
                    navController.navigate(FitnessScreen.CategoryScreen.route)

                },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB2CCCC),
                        contentColor = Color.Black
                    )
                ) {
                    androidx.compose.material3.Text("Start Exercises",fontWeight = FontWeight.Bold, fontSize = 15.sp)

                }
                Spacer(modifier = Modifier.widthIn(25.dp))
                androidx.compose.material3.Button(onClick = {
                    navController.navigate(FitnessScreen.BmiCalculator.route)
                    
                },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB2CCCC),
                        contentColor = Color.Black
                        
                    )
                ) {
                    androidx.compose.material3.Text("BMI ", fontWeight = FontWeight.Bold, fontSize = 15.sp)

                }

            }
            androidx.compose.material3.Button(onClick = {
                navController.navigate(FitnessScreen.HistoryScreen.route)

            },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB2CCCC),
                    contentColor = Color.Black

                )
            ) {
                androidx.compose.material3.Text("See History  ", fontWeight = FontWeight.Bold, fontSize = 15.sp)

            }
        }
    }

}