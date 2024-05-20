package com.example.sample

sealed class UiEvent {


    data class FirstNameChanged(val firstName : String) : UiEvent()
    data class LastNameChanged(val lastName : String) : UiEvent()
    data class EmailChanged(val email : String)  : UiEvent()
    data class PasswordChanged(val password : String) : UiEvent()

    object RegistrationButtonClicked : UiEvent()

}