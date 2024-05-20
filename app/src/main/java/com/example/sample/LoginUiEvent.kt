package com.example.sample

sealed class LoginUiEvent {
    data class EmailChanged(val email : String) : LoginUiEvent()
    data class PasswordChanged(val password : String) : LoginUiEvent()

    object LoginButtonClicked  : LoginUiEvent()
}