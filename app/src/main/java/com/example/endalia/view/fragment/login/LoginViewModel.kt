package com.example.endalia.view.fragment.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.endalia.controllers.AppDatabase
import com.example.endalia.util.Singleton

class LoginViewModel: ViewModel() {

    private val _userEmail = MutableLiveData<String>()
    private val _userPass = MutableLiveData<String>()
    private val _loginSuccess = MutableLiveData<LoginState>()
    private lateinit var _database: AppDatabase

    enum class LoginState  {
        EmailError,
        PasswordError,
        BothError,
        None,
        Success,
        UserNotFoundError
    }

    val userEmail: LiveData<String> = _userEmail
    val userPass: LiveData<String> = _userPass
    val loginSuccess: LiveData<LoginState> = this._loginSuccess

    fun configViewmodel(context: Context) {
        _database = setupDb(context)
    }

    fun setUserEmail(email: String) {
        _userEmail.value = email
    }

    fun setUserPass(password: String) {
        _userPass.value = password
    }

    fun buttonClicks() {

    }

    fun performLogin() {
        if (!fieldsCorrect()) {
            return
        }
        val empleado = _database.empleadoDao().findEmployeeByEmail(_userEmail.value!!)
        if (empleado == null || empleado.contraseña != _userPass.value) {
            this._loginSuccess.value = LoginState.UserNotFoundError
            return
        }
        // Setear empleado en Singleton y cambiar a pantalla de home
        Singleton.employee = empleado

    }

    private fun setupDb(context: Context): AppDatabase{
        return  Room.databaseBuilder(
            context,
            AppDatabase::class.java, "endalia-db"
        ).build()
    }

    private fun fieldsCorrect(): Boolean {
        if (_userEmail.value == null || _userPass.value == null) {
            this._loginSuccess.value = LoginState.BothError
            return false
        }
        this._loginSuccess.value = LoginState.None
        return true
    }
}