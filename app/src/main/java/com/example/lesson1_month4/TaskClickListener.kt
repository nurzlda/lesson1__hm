package com.example.lesson1_month4

import com.example.lesson1_month4.room.TaskModel

interface TaskClickListener {

    fun itemClick(taskModel: TaskModel)
    fun deleteItemClick(taskModel: TaskModel)
}