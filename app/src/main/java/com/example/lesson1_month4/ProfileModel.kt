package com.example.lesson1_month4

data class ProfileModel(
    val accountName : String,
    val date : Int? = null,
    val tasks : Int? = null,
    val password : String? = null
)
