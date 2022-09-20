package com.example.golderform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class SugarcaneDetails : AppCompatActivity() {
    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    val vettal_name = arrayOf("Arumugam","Vellaiammal","Others1","Others2")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugarcane_details)
        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        val dropspinner_vettal = findViewById<Spinner>(R.id.spinner2)
        val shopAdapter_vettal = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,vettal_name)
        dropspinner_vettal.adapter = shopAdapter_vettal

    }
}