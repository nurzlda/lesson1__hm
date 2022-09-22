package com.example.lesson1_month4

import java.io.Serializable

data class TaskModel(
    val task : String,
    val regular : String,
    val date : String
):Serializable
