package com.example.golderform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val greeting = findViewById<Button>(R.id.login)
        val email_id = findViewById<EditText>(R.id.emailid)
        val password_id = findViewById<EditText>(R.id.passwordid)
        greeting.setOnClickListener{
            val intent = Intent(this,Verification::class.java)
            startActivity(intent)
        }
    }
}