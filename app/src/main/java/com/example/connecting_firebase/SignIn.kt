package com.example.connecting_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    private lateinit var database:DatabaseReference
    companion object{
        const val key1 = "com.example.Connecting_firebase.SignIn.name"
        const val key2 = "com.example.Connecting_firebase.SignIn.mail"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val sd = findViewById<Button>(R.id.button)
        val kd = findViewById<TextInputEditText>(R.id.ints)
        val pd = findViewById<TextInputEditText>(R.id.ints1)
        sd.setOnClickListener {
            val rs = kd.text.toString()
            val password = pd.text.toString()
            if(rs.isNotEmpty()&&password.isNotEmpty()){
                readData(rs, password)
            }else{
                Toast.makeText(this, "Please enter your email or password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(rs: String , password:String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(rs).get().addOnSuccessListener{
            if(it.exists()){
                // Welcome Screen with using intent
                database.child(rs).child(password).get().addOnSuccessListener {
                    if (it.exists()){
                        val name = it.child("name").value
                        val email = it.child("email").value
                        val welcomeIntent = Intent(this, WelcomeScreen::class.java)
                        welcomeIntent.putExtra(key1, name.toString())
                        welcomeIntent.putExtra(key2, email.toString())
                        startActivity(welcomeIntent)
                    }else{
                        Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}