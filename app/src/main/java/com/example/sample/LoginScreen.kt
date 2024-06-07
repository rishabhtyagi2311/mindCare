package com.example.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlin.math.log


@Composable
fun LoginScreen(navController: NavHostController)
{
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(2.dp),
        contentAlignment = Alignment.Center){
        val loginViewModel : LoginViewModel = viewModel()


        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            val localFocusManager = LocalFocusManager.current
            Column(modifier = Modifier
                .fillMaxSize()
                .clickable { localFocusManager.clearFocus() }) {

                NormalTextComponent(value = "Hey There,")
                HeadingTextComponent(value = "Welcome Back")
                Spacer(modifier = Modifier.height(20.dp))
                MyTextField(labelValue = "Email", onTextSelected = {
                    loginViewModel.onEvent(LoginUiEvent.EmailChanged(it))

                },
                    errorStatus = loginViewModel.loginUiState.value.emailError)
                PasswordTextField(labelValue = "Password", onTextSelected = {
                    loginViewModel.onEvent(LoginUiEvent.PasswordChanged(it))
                },
                    errorStatus = loginViewModel.loginUiState.value.passwordError)

                Spacer(modifier = Modifier.height(20.dp))

                ButtonComponent(value = "Login", onButtonClicked = {
                    loginViewModel.onEvent(LoginUiEvent.LoginButtonClicked)
                    if(loginViewModel.loginComplete.value)
                    {
                        navController.navigate(ExternalScreens.MainScreen.route)
                    }

                },
                    isEnabled = loginViewModel.allValidationPassed.value)

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()
                Spacer(modifier = Modifier.height(20.dp))
                ButtonComponent(value = "Register", onButtonClicked = {
                        navController.navigate(ExternalScreens.SignUpScreen.route)
                },
                    isEnabled = true)


            }

        }

        if(loginViewModel.loginInProgress.value)CircularProgressIndicator()
    }
}

@Preview
@Composable
fun LoginScreenPreview()
{
    LoginScreen(rememberNavController())
}