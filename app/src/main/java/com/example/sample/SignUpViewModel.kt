package com.example.sample

import androidx.collection.emptyLongSet
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.ktx.Firebase


class SignUpViewModel :  ViewModel(){

    var registrationUiState = mutableStateOf(RegistrationUiState())
    var allValidationPassed = mutableStateOf(false)


    var signUpProcess = mutableStateOf(false)
    fun onEvent(event: UiEvent)
    {
        validateDataWithRules()
        when(event)
        {
            is UiEvent.FirstNameChanged->{
                registrationUiState.value = registrationUiState.value.copy(
                    firstName = event.firstName
                )

            }
            is UiEvent.LastNameChanged->{
                registrationUiState.value = registrationUiState.value.copy(
                    lastName = event.lastName
                )

            }
            is UiEvent.EmailChanged->{
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email

                )

            }
            is UiEvent.PasswordChanged->{
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )

            }

            is UiEvent.RegistrationButtonClicked ->{
                signUp()
            }
        }

    }
    private fun signUp()
    {
       createUserInFirebase(email = registrationUiState.value.email,
           registrationUiState.value.password
           )

    }

    private fun validateDataWithRules()
    {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUiState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUiState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = registrationUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUiState.value.password
        )

        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status

        )

        if(fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status)
        {
            allValidationPassed.value = true
        }
        else{
            allValidationPassed.value = false
        }
    }

    private  fun createUserInFirebase(email : String , password : String)
    {
        signUpProcess.value = true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                signUpProcess.value = false
            }
            .addOnFailureListener { }
    }

    fun logout()
    {
        val firebaseAuth  = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{}
        firebaseAuth.addAuthStateListener(authStateListener)
    }


}