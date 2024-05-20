package com.example.sample

import android.os.Build
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sample.components.ButtonComponent
import com.example.sample.components.DividerTextComponent
import com.example.sample.components.HeadingTextComponent
import com.example.sample.components.MyTextField
import com.example.sample.components.NormalTextComponent
import com.example.sample.components.PasswordTextField


@Composable
fun SignUpScreen (navController: NavHostController ){


    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        val signUpVM: SignUpViewModel = viewModel()
        Surface (color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp))
        {


            val localFocusManager = LocalFocusManager.current

            Column(modifier = Modifier
                .fillMaxSize()
                .clickable { localFocusManager.clearFocus() })

            {
                Spacer(modifier = Modifier.height(15.dp))
                NormalTextComponent(value = "Hey There,")
                HeadingTextComponent(value = "Create an Account")
                Spacer(modifier = Modifier.height(20.dp))
                MyTextField(
                    labelValue = "First Name", onTextSelected = {
                        signUpVM.onEvent(UiEvent.FirstNameChanged(it))

                    },
                    errorStatus = signUpVM.registrationUiState.value.firstNameError
                )
                MyTextField(
                    labelValue = "Last Name", onTextSelected = {
                        signUpVM.onEvent(UiEvent.LastNameChanged(it))

                    },
                    errorStatus = signUpVM.registrationUiState.value.lastNameError
                )
                MyTextField(
                    labelValue = "Email", onTextSelected = {
                        signUpVM.onEvent(UiEvent.EmailChanged(it))

                    },
                    errorStatus = signUpVM.registrationUiState.value.emailError
                )
                PasswordTextField(
                    labelValue = "Password", onTextSelected = {
                        signUpVM.onEvent(UiEvent.PasswordChanged(it))

                    },
                    errorStatus = signUpVM.registrationUiState.value.passwordError
                )
                Spacer(modifier = Modifier.height(70.dp))
                ButtonComponent(
                    value = "Register",
                    onButtonClicked = {
                        signUpVM.onEvent(UiEvent.RegistrationButtonClicked)
                    },
                    isEnabled = signUpVM.allValidationPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                Spacer(modifier = Modifier.height(20.dp))
                ButtonComponent(
                    value = "Login", onButtonClicked = {
                        navController.navigate(ExternalScreens.LoginScreen.route)

                    },
                    isEnabled = true
                )


            }
        }

        if(signUpVM.signUpProcess.value)
        {
            CircularProgressIndicator()
        }


    }

}


@Preview
@Composable
fun DefaultPreviewOfSignUp()
{
    SignUpScreen(rememberNavController())
}