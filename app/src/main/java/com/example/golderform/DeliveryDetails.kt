package com.example.golderform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class DeliveryDetails : AppCompatActivity() {
    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)

        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter
    }
}