package com.example.endalia.controllers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.endalia.models.Empleado
import com.example.endalia.models.EmpleadoDao

@Database(entities = [Empleado::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun empleadoDao(): EmpleadoDao
}