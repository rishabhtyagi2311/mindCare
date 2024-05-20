package com.example.sample

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

var selectedID = ""
@Composable
fun setTimer(navController: NavHostController)
{
    val viewmodel : FitnessViewModel = viewModel()
    viewmodel.fetchExercises(selectedID)
    val exercises by viewmodel.exercisesState.collectAsState()
    setExercises  =  exercises
    var duration by remember {
        mutableStateOf("")
    }
    var gap by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    Box(modifier  = Modifier
        .fillMaxSize()
        .clickable { focusManager.clearFocus() }) {
        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(18.dp))
            androidx.compose.material3.Text(
                "   Exercises for your Selected Category are: ",

                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Cyan),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(18.dp))

            LazyColumn {
                items(exercises)
                {exercise->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            ,
                        shape = RoundedCornerShape(8.dp),
                        border =  BorderStroke(2.dp, Color.Cyan),
                        elevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = exercise.name,
                                style = MaterialTheme.typography.h6,
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            androidx.compose.material3.Text(
                "                     Set Your Parameters  ",

                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Cyan),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = duration, onValueChange = {
                                                                duration=it
            },
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                label = { androidx.compose.material3.Text("Enter the Exercise Duration") },
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(15.dp))
            OutlinedTextField(value = gap, onValueChange = {gap = it},
                modifier = Modifier.fillMaxWidth(),
                label = { androidx.compose.material3.Text("Enter the Break Duration") },
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(15.dp))
            Spacer(modifier = Modifier.height(10.dp))
            androidx.compose.material3.Button(onClick =
            {
                Duration = duration.toInt()
                Gaps = gap.toInt()
                navController.navigate(FitnessScreen.DisplayExercise.route)

            },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB2CCCC),
                    contentColor = Color.Black
                )
            )
            {
                androidx.compose.material3.Text("Start Exercises",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic)

            }
        }
        
    }

}