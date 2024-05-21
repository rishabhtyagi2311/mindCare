package com.example.sample

import java.text.SimpleDateFormat
import java.util.*

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun BmiCalculator(navController: NavHostController)
{
    var height by remember {
        mutableStateOf("")
    }
    var weight  by remember {
        mutableStateOf("")
    }
    var bmiResult by remember {
        mutableStateOf<String?>(null)
    }
    var bmiMessage by remember {
        mutableStateOf<String?>(null)
    }

    val focus = LocalFocusManager.current
    Box(modifier = Modifier.fillMaxSize().clickable { focus.clearFocus() }){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(30.dp))

            Text("     Enter your Height and ---------------",

                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 10.dp)
                    .background(color = Color.Cyan),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic)
            Text("           ---------------Weight to get BMI ",

                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 10.dp)
                    .background(color = Color.Cyan),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic)

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = R.drawable.bmi_mindcare),
                contentDescription = "fitness_logo",
                modifier = Modifier
                    .fillMaxWidth().heightIn(250.dp,320.dp)

            )

            OutlinedTextField(value = height, onValueChange =
            {
                height= it
            },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp),
                label = {Text("Height in Centimeters")},
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(15.dp)



            )
            OutlinedTextField(value = weight, onValueChange =
            {
                weight= it
            },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp),
                label = {Text("Weight in Kilograms ")},
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(15.dp)



            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)

            {
                Button(onClick = {

                    val heightValue = height.toFloatOrNull()
                    val weightValue = weight.toFloatOrNull()

                    if (heightValue != null && weightValue != null && heightValue > 0) {
                        // Calculate BMI
                        val heightInMeters = heightValue / 100
                        val bmi = weightValue / (heightInMeters * heightInMeters)
                        bmiResult = "Your BMI is %.2f".format(bmi)
                        bmiMessage = getBmiMessage(bmi)

                    } else {
                        bmiResult = "Please enter valid height and weight"
                        bmiMessage = null
                    }
                },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB2CCCC),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Calculate",fontWeight = FontWeight.Bold, fontSize = 15.sp)

                }
                Spacer(modifier = Modifier.widthIn(25.dp))
                Button(onClick = {
                    val heightValue = height.toFloat()
                    val weightValue = weight.toFloat()
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val currentDate = sdf.format(Date())
                    val bmiDetails = weightValue.let {
                        bmiResult?.let { it1 ->
                            BmiElement(
                                date = currentDate.toString(),
                                height = heightValue,
                                weight = it,
                                bmi = it1,
                                message = bmiMessage ?: ""
                            )
                        }
                    }
                    val repository = HistoryRepo()
                    if (bmiDetails != null) {
                        repository.addUserBmiEntry(unique, bmiDetails) { success ->
                            if (success) {
                                height = ""
                                weight = ""
                                bmiMessage = ""
                                bmiResult = ""
                            }
                        }
                    }
                   height = ""
                    weight = ""
                    bmiMessage=""
                    bmiResult=""



                },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB2CCCC),
                        contentColor = Color.Black

                    )
                ) {
                    Text("Reset ", fontWeight = FontWeight.Bold, fontSize = 15.sp)

                }

            }
            bmiResult?.let {
                Spacer(modifier = Modifier.height(20.dp))
                Text(it, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Red)
            }

            bmiMessage?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(it, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Blue, modifier = Modifier.padding(5.dp))
            }


        }
    }
}


fun getBmiMessage(bmi: Float): String {
    return when {
        bmi < 18.5 -> "You are underweight. Consider eating a balanced diet with more calories."
        bmi in 18.5..24.9 -> "You have a normal weight. Maintain your current lifestyle."
        bmi in 25.0..29.9 -> "You are overweight. Consider exercising more and eating a balanced diet."
        bmi >= 30.0 -> "You are obese. It's recommended to consult with a healthcare provider for personalized advice."
        else -> "Invalid BMI"
    }
}
@Preview
@Composable
fun PreviewBmi()
{
    BmiCalculator(navController = rememberNavController())
}