package com.example.sample

data class UserArchiveEntry (
    val password : String = "",
    val date : String = "",
    val title: String = "",
    val description : String = "",
    val images: List<String> = emptyList()
)