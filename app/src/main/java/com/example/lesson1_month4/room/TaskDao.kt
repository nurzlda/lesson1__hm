package com.example.lesson1_month4.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao

interface TaskDao {

    @Insert
    fun insert(taskModel: TaskModel)

    @Query("SELECT * FROM task_model")
    fun getAll() : LiveData<List<TaskModel>>

    @Update
    fun update(taskModel: TaskModel)

    @Delete
    fun delete(taskModel: TaskModel)
}