package com.example.sample.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun NormalTextComponent(value: String)
{
    Text(
     text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center


    )

}


@Composable
fun HeadingTextComponent(value: String)
{
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center


    )

}



@Composable
fun MyTextField(labelValue : String,onTextSelected: (String)->Unit,
                errorStatus : Boolean=  false)
{
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier =Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange =
        { textValue.value = it
            onTextSelected(it)

        },
        shape = RoundedCornerShape(15.dp),
        label = { Text(text = labelValue) },
        keyboardOptions = KeyboardOptions.Default,
        isError = !errorStatus
    )
}



@Composable
fun PasswordTextField(labelValue : String ,onTextSelected: (String)->Unit,
                      errorStatus : Boolean)
{
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember{
        mutableStateOf(false)
    }



    OutlinedTextField(
        modifier =Modifier.fillMaxWidth(),
        value = password.value,
        onValueChange = { password.value = it
                        onTextSelected(it)},
        label = { Text(text = labelValue) },
        shape = RoundedCornerShape(15.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val imageIcon =
                if(passwordVisible.value)
                {
                    Icons.Filled.Visibility

                }
                else
                {
                    Icons.Filled.VisibilityOff
                }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {

                Icon(imageVector = imageIcon, contentDescription = "Password")


            }

        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus




    )
}

@Composable
fun ButtonComponent(value  : String, onButtonClicked : () -> Unit, isEnabled:Boolean = false)
{
    Button(onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(Color.Transparent)

        ) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Color.Cyan, Color(0xFFB2CCCC))),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold)

        }
    }
}

@Composable
fun DividerTextComponent()
{
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.DarkGray,
            thickness = 1.dp
        )
        
        Text(modifier = Modifier.padding(8.dp), text = "or" , fontSize = 16.sp,color = Color.Black)

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.DarkGray,
            thickness = 1.dp
        )


    }

}