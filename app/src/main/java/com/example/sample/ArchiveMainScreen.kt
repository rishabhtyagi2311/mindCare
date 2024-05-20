package com.example.sample


import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.sample.R

var unique= ""
@Composable
fun HappyPlacesHome(navController: NavHostController)
{


    val focusManager = LocalFocusManager.current
    val archiveView : ArchiveViewModel = viewModel()
    var selectedImageUris by remember{ mutableStateOf<List<Uri>>(emptyList()) }
    var title by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    var selected by remember{ mutableStateOf(Color.Cyan) }


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {uris -> selectedImageUris = uris})
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .clickable { focusManager.clearFocus() })
    {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){


            Spacer(modifier = Modifier.height(18.dp))
            Text("     Capture and Cherish---------------",

                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Cyan),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic)
            Text("           ---------------Your Personal Journal",

                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Cyan),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(18.dp))


            OutlinedTextField(value = title, onValueChange =
                {
                    title = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {Text("Title")},
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(15.dp)



            )
            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(value = date, onValueChange =
            {
                date = it
            },
                modifier = Modifier.fillMaxWidth(),
                label = {Text("Date")},
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(15.dp)



                )
            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(value = description, onValueChange =
            {
                description = it
            },
                modifier = Modifier.fillMaxWidth(),
                label = {Text("Description")},
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(15.dp)



                )

            Spacer(modifier = Modifier.height(18.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(15.dp)
                ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Spacer(modifier = Modifier.height(2.dp))
                Column {
                    Text("  Click the Gallery icon and ",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp
                    )
                    Text("  add upto 5 images",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(80.dp))
                Image(painter = painterResource(id = R.drawable.add_image), contentDescription = "add_images",
                    modifier  = Modifier
                        .size(60.dp)
                        .clickable {
                            galleryLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)                             )
                        })
            }

            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material3.Button(onClick =
            {


                Log.d("", unique)
                val entry  = unique?.let {
                    UserArchiveEntry(
                        date = date,
                        title =  title,
                        description = description,
                        password = it,
                        images = uriListToStringList(selectedImageUris)
                        )
                }
                unique?.let {
                    if (entry != null) {
                        archiveView.addUserEntry(it,entry){success->
                            if(success)
                            {
                                Log.d("" , "Entry done")
                            }
                            else{
                                Log.d("","Entry process failed")
                            }
                        }
                    }
                }

            }
                ,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB2CCCC),
                    contentColor = Color.White
                )
            ) {
                Text("Save")

            }


            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier

                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
            {

                Text(text = "                                                            ")
                androidx.compose.material3.Button(onClick = {
                    navController.navigate(HappyScreen.ArchiveScreen.route)
                    selected = Color(0xFFB2CCCC)
                },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = selected,
                        contentColor = Color.White
                    )
                ) {
                    Text("Archives")

                }
            }


        }
    }


}
fun uriListToStringList(uriList: List<Uri>): List<String> {
    return uriList.map { uri -> uri.toString() }
}


@Preview
@Composable
fun HappyPreview()
{
    HappyPlacesHome(navController = rememberNavController())
}