package com.example.endalia.models

import androidx.room.*

@Entity(tableName = "empleados")
data class Empleado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val puestoTrabajo: String,
    val telefono: String,
    val correo: String,
    val retrato: String
)

@Dao
interface EmpleadoDao {
    @Query("SELECT * FROM empleados")
    fun getAll(): List<Empleado>

    @Insert
    fun insert(empleado: Empleado)

    @Delete
    fun delete(empleado: Empleado)
}