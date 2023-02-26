package com.example.endalia.view.fragment.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endalia.models.Employee
import com.example.endalia.util.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UserListViewModel: ViewModel() {
    private val _users = MutableLiveData<List<Employee>>()


    val users: LiveData<List<Employee>> = _users

    suspend fun getUsers() {
        var dbUsers: List<Employee>? = null
        runBlocking {
            withContext(Dispatchers.IO) {
                dbUsers = Singleton.database?.empleadoDao()?.getAll()
            }
        }
        this._users.value = dbUsers
    }
}