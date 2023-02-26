package com.example.endalia.util

import com.example.endalia.controllers.AppDatabase
import com.example.endalia.models.Employee

object Singleton {
    var employee: Employee? = null
    var database: AppDatabase? = null
}