package com.example.sample

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import kotlinx.coroutines.delay


var setExercises : List<exercises> = emptyList()
var Duration : Int= 0
var Gaps : Int = 0
@Composable
fun display(navController: NavHostController)
{
    var number by remember {
        mutableStateOf(-1)
    }
    var doing by remember {
        mutableStateOf(false)
    }

        if (number<setExercises.size-1 && !doing) {
            var counter by remember {
                mutableIntStateOf(Gaps)
            }
            LaunchedEffect(key1 = counter) {
                if (counter > 0) {
                    delay(1000L)  // Wait for 1 second
                    counter--


                }
                if (counter == 0) {

                    doing = !doing
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    androidx.compose.material3.Text(
                        " Your Upcoming Exercise is: ",

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Cyan),

                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic
                    )
                    val exe = setExercises[number + 1].name
                    androidx.compose.material3.Text(
                        " $exe ",

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Cyan).align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic
                    )
                    val difficulty = setExercises[number+1].difficulty
                    androidx.compose.material3.Text(
                        " Difficulty:  $difficulty ",

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Cyan).align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic
                    )

                    Text(
                        "$counter",
                        fontSize = 80.sp,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )


                }
            }
        }
        else if(number< setExercises.size && doing)
        {

            var counter by remember {
                mutableStateOf(Duration)
            }
            LaunchedEffect(key1 = counter) {
                if (counter > 0) {
                    delay(1000L)  // Wait for 1 second
                    counter--


                }
                if (counter == 0) {
                    number++
                    doing = !doing
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val exe = setExercises[number + 1].name
                    androidx.compose.material3.Text(
                        " $exe ",

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Cyan).align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    val painter = rememberAsyncImagePainter(model = setExercises[number + 1].url)
                    Image(
                        painter = painter, contentDescription = "",
                        modifier = Modifier.size(width = 350.dp, height = 350.dp)
                    )


                    Text(
                        "$counter",
                        fontSize = 80.sp,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )

                    val des = setExercises[number + 1].description
                    androidx.compose.material3.Text(
                        " $des ",

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Cyan)
                            .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic
                    )

                }
            }


        }

    else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                androidx.compose.material3.Text(
                    "           You Are Done for this set",

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Cyan),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }


}