package com.example.endalia.view.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endalia.controllers.AppDatabase
import com.example.endalia.util.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    private val _userEmail = MutableLiveData<String>()
    private val _userPass = MutableLiveData<String>()
    private val _loginSuccess = MutableLiveData<LoginState>()

    enum class LoginState {
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

    fun setUserEmail(email: String) {
        _userEmail.value = email
    }

    fun setUserPass(password: String) {
        _userPass.value = password
    }

    suspend fun performLogin() {
        if (!fieldsCorrect()) {
            return
        }
        withContext(Dispatchers.IO) {
        val empleado = Singleton.database?.empleadoDao()?.findEmployeeByEmail(_userEmail.value!!)
            withContext(Dispatchers.Main) {
                if (empleado == null || empleado.password != _userPass.value) {
                    _loginSuccess.value = LoginState.UserNotFoundError
                    return@withContext
                }
            // Setear empleado en Singleton y cambiar a pantalla de home
            Singleton.employee = empleado
            _loginSuccess.value = LoginState.Success
            }
        }
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