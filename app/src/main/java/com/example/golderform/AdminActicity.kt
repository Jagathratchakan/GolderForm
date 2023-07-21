package com.example.golderform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminActicity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_acticity)
        val sugarcanebtn = findViewById<Button>(R.id.sugar_details)
        val aska_sugar_btn = findViewById<Button>(R.id.aska_sugar)
        val Nattu_Sugar_btn = findViewById<Button>(R.id.Nattu_Sugar)
        val mattai_btn = findViewById<Button>(R.id.mattai)
        val others_btn = findViewById<Button>(R.id.others)
        val Allai_details_btn = findViewById<Button>(R.id.Allai_details)
        val Stock_details_btn = findViewById<Button>(R.id.Stock_details)
        val delivery_btn = findViewById<Button>(R.id.delivery)
        val consolidated_btn = findViewById<Button>(R.id.consolidated)
        val back_btn = findViewById<Button>(R.id.back_home)

        sugarcanebtn.setOnClickListener{
            val intent1= Intent(this,SugarcaneDetails::class.java)
            startActivity(intent1)
        }

        aska_sugar_btn.setOnClickListener{
            val intent_var = Intent(this,AskaSugarDetails::class.java)
            startActivity(intent_var)
        }

        Nattu_Sugar_btn.setOnClickListener{
            val intent_var = Intent(this,NattuSakkaraiDetails::class.java)
            startActivity(intent_var)
        }
        mattai_btn.setOnClickListener{
            val intent_var = Intent(this,MattaiDetails::class.java)
            startActivity(intent_var)
        }

        others_btn.setOnClickListener{
            val intent_var = Intent(this,OtherDetails::class.java)
            startActivity(intent_var)
        }

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