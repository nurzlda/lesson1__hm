package com.example.lesson1_month4

import android.app.Application
import androidx.room.Room
import com.example.lesson1_month4.room.AppDataBase

class App : Application() {

    companion object{
        lateinit var appDataBase: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        appDataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
}