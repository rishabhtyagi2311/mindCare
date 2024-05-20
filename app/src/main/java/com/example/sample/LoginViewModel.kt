package com.example.sample

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel  : ViewModel(){




    private val TAG = LoginViewModel::class.simpleName

    var loginUiState = mutableStateOf(LoginUiState())
    var allValidationPassed = mutableStateOf(false)
    var loginComplete  = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    fun onEvent(event : LoginUiEvent)
    {
        validateLoginData()
        when(event)
        {
            is LoginUiEvent.EmailChanged ->
            {
                loginUiState.value = loginUiState.value.copy(
                    email =  event.email
                )

            }

            is LoginUiEvent.PasswordChanged->
            {
                loginUiState.value = loginUiState.value.copy(
                    password =  event.password
                )

            }

            is LoginUiEvent.LoginButtonClicked->
            {
                login()

            }


        }
    }


    private fun validateLoginData()
    {
        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationPassed.value = emailResult.status && passwordResult.status
    }
    private fun login(){
        loginInProgress.value  = true
        val email = loginUiState.value.email
        val password = loginUiState.value.password
        viewModelScope.launch {
            try {
                val authResult = FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .await() // await the result

                Log.d(TAG, "login complete")
                unique = password// Assign the received password to _password
                loginComplete.value = true
            } catch (e: Exception) {
                Log.e(TAG, "Login failed: $e")
                // Handle login failure if needed
            } finally {
                loginInProgress.value = false
            }
        }
    }
}