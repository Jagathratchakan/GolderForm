package com.example.golderform

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class flashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashscreen)

        Handler(Looper.getMainLooper()).postDelayed({
            val current = FirebaseAuth.getInstance().currentUser
            if(current == null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this, Verification::class.java)
                startActivity(intent)
            }
            finish()
        }, 1000)
    }
}