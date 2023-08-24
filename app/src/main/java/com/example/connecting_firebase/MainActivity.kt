package com.example.connecting_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sd = findViewById<EditText>(R.id.ints)
        val dd = findViewById<EditText>(R.id.ints1)
        val fd = findViewById<EditText>(R.id.ints2)
        val gd = findViewById<Button>(R.id.button1)
        val ss = findViewById<TextView>(R.id.signin)
        gd.setOnClickListener {
            val name  = sd.text.toString()
            val email = dd.text.toString()
            val password = fd.text.toString()
            val userdata = user_data(name, email, password)
            sd.text?.clear()
            dd.text?.clear()
            fd.text?.clear()
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).setValue(userdata)
            Toast.makeText(this, "registered",Toast.LENGTH_SHORT).show()
        }
        ss.setOnClickListener {
            intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }
    }
}