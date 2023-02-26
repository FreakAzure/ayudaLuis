package com.example.endalia.models

import androidx.room.*

@Entity(tableName = "Employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val surname: String,
    val job: String,
    val phone: String,
    val email: String,
    val photo: Int,
    val password: String
)

@Dao
interface EmpleadoDao {
    @Query("SELECT * FROM Employees")
    fun getAll(): List<Employee>

    @Insert
    fun insert(employee: Employee)

    @Delete
    fun delete(employee: Employee)

    @Query("SELECT * FROM Employees WHERE email = :email")
    fun findEmployeeByEmail(email: String): Employee?
}