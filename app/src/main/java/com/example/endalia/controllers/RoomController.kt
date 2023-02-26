package com.example.endalia.controllers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.endalia.models.Employee
import com.example.endalia.models.EmpleadoDao

@Database(entities = [Employee::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun empleadoDao(): EmpleadoDao
}