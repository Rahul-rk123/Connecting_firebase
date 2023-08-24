package com.example.connecting_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        val name = intent.getStringExtra(SignIn.key1)
        val mail = intent.getStringExtra(SignIn.key2)
        val wltext = findViewById<TextView>(R.id.text1)
        val near = findViewById<TextView>(R.id.text2)
        val dl = findViewById<TextView>(R.id.text3)
        wltext.text = "Welcome $name"
        dl.text = "Name: $name"
        near.text = "Mail: $mail"
    }
}