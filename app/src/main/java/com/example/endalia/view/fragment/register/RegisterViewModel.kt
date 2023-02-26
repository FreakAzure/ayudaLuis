package com.example.endalia.view.fragment.register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.endalia.models.Empleado
import com.example.endalia.controllers.AppDatabase

class RegisterViewModel : ViewModel() {

    private lateinit var _database: AppDatabase
    private val _userEmail = MutableLiveData<String>()
    private val _userPass = MutableLiveData<String>()
    private val _confirmUserPass = MutableLiveData<String>()
    private val _registerSuccess = MutableLiveData<RegisterState>()


    enum class RegisterState  {
        EmailError,
        PasswordError,
        ConfirmPasswordError,
        BothError,
        None,
        Success,
        UserAlreadyExistsError
    }
    val userEmail: LiveData<String> = _userEmail
    val userPass: LiveData<String> = _userPass
    val confirmPass: LiveData<String> = _confirmUserPass
    val registerSuccess: LiveData<RegisterState> = _registerSuccess

    fun configViewmodel(context: Context) {
        _database = setupDb(context)
    }

    fun setUserEmail(email: String) {
        _userEmail.value = email
    }

    fun setUserPass(password: String) {
        _userPass.value = password
    }

    fun setConfirmPass(password: String) {
        _confirmUserPass.value = password
    }

    fun buttonClicks() {

    }

    fun performRegister() {
        if (!fieldsCorrect()) {
            return
        }
        val empleado = _database.empleadoDao().findEmployeeByEmail(_userEmail.value!!)
        if (empleado != null) {
            this._registerSuccess.value = RegisterState.UserAlreadyExistsError
            return
        }
        val newEmpleado = Empleado(correo = _userEmail.value!!, contraseña = _userPass.value!!)
        _database.empleadoDao().insert(newEmpleado)
        this._registerSuccess.value = RegisterState.Success
    }
    private fun setupDb(context: Context): AppDatabase {
        return  Room.databaseBuilder(
            context,
            AppDatabase::class.java, "endalia-db"
        ).build()
    }
    private fun fieldsCorrect(): Boolean {
        if (_userEmail.value == null || _userPass.value == null || _confirmUserPass.value == null) {
            this._registerSuccess.value = RegisterState.BothError
            return false
        }
        if (!isValidEmail(_userEmail.value!!)) {
            this._registerSuccess.value = RegisterState.EmailError
            return false
        }
        if (!isValidPassword(_userPass.value!!)) {
            this._registerSuccess.value = RegisterState.PasswordError
            return false
        }
        if (_userPass.value != _confirmUserPass.value) {
            this._registerSuccess.value = RegisterState.ConfirmPasswordError
            return false
        }
        this._registerSuccess.value = RegisterState.None
        return true
    }
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}