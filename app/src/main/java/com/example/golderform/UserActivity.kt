package com.example.golderform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val Allai_details_btn = findViewById<Button>(R.id.Allai_details)
        val Stock_details_btn = findViewById<Button>(R.id.Stock_details)
        val delivery_btn = findViewById<Button>(R.id.delivery)
        val consolidated_btn = findViewById<Button>(R.id.consolidated)
        val back_btn = findViewById<Button>(R.id.back_home)

        Allai_details_btn.setOnClickListener{
            val intent_var = Intent(this,AllaiRunningDetails::class.java)
            startActivity(intent_var)
        }

        Stock_details_btn.setOnClickListener{
            val intent_var = Intent(this,StockDetails::class.java)
            startActivity(intent_var)
        }

        delivery_btn.setOnClickListener{
            val intent_var = Intent(this,DeliveryDetails::class.java)
            startActivity(intent_var)
        }

        /*consolidated_btn.setOnClickListener{
            val intent_var = Intent(this,ConsolidatedDetails::class.java)
            startActivity(intent_var)
        }*/
    }
}