package com.example.sample

data class catgeoryExercises(

    val name : String = "",
    val id : String = "",
    val description  : String = ""

)

data class exercises(
    val name : String ="",
    val description  :String = "",
    val difficulty : String = "",
    val url : String = "",
    val documentId : String = ""
)

data class exerciseItem(
    val name : String ="",
    val description: String="",
    val image : String = "",

)