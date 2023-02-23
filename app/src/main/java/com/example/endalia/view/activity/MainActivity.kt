package com.example.endalia.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.endalia.R
import com.example.endalia.util.Singleton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Singleton.employee != null) {
            // Navegar a Home
        } else {
            // Navegar a login
        }
    }
}