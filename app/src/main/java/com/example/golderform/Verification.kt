package com.example.golderform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class Verification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val verifybtn = findViewById<Button>(R.id.btnVerify)

        verifybtn.setOnClickListener{
            val intentVar = Intent(this,AdminActicity::class.java)
            startActivity(intentVar)
        }
    }
}