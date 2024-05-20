package com.example.sample

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun  CategorySelection(navController: NavHostController) {

    val viewModel : FitnessViewModel = viewModel()
    viewModel.fetchCategories()
    val categories by viewModel.categoriesState.collectAsState()
    var selectedCategoryId by remember { mutableStateOf<String?>("Proceed") }

    Box(modifier  = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {


            Spacer(modifier = Modifier.height(18.dp))
            androidx.compose.material3.Text(
                "   Choose Your Exercise Category------------",

                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Cyan),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),

                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(
                        category = category,
                        isSelected = category.name == selectedCategoryId,
                        onClick = {
                            selectedCategoryId = category.name
                            selectedID = category.id

                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material3.Button(onClick =
                {
                    navController.navigate(FitnessScreen.SetTimerScreen.route)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB2CCCC),
                    contentColor = Color.White
                )
            )
            {
                androidx.compose.material3.Text("$selectedCategoryId",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic)

            }
        }
    }
}
@Composable
fun CategoryCard(category: catgeoryExercises, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = if (isSelected) BorderStroke(2.dp, Color.Cyan) else null,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp,
                color = if (isSelected) Color.Cyan else Color.Cyan
            )
            Text(
                text = category.description,
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp,
                color = if (isSelected) Color.Black else Color.Black
            )
            
        }
    }
}
