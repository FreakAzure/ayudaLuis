package com.example.endalia.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.endalia.R
import com.example.endalia.controllers.AppDatabase
import com.example.endalia.util.Singleton
import com.example.endalia.view.fragment.login.LoginFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Singleton.database = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "endalia-db").build()
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,LoginFragment()).commit()
    }
}