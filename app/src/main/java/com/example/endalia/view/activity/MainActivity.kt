package com.example.endalia.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.endalia.R
import com.example.endalia.util.Singleton
import com.example.endalia.view.fragment.login.LoginFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Singleton.employee != null) {
            // Navegar a home
            }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.nav_login,LoginFragment()).commit()
        }
    }
}