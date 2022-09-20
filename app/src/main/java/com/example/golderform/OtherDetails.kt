package com.example.golderform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class OtherDetails : AppCompatActivity() {

    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    val kg = arrayOf("1Kg","2Kg","5Kg","25Kg","50Kg","100Kg")
    val work = arrayOf("Male","Female")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_details)

        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        val drop_spinner_worker = findViewById<Spinner>(R.id.spinner_worker)
        val shopAdapter_worker= ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,work)
        drop_spinner_worker.adapter = shopAdapter_worker

        val drop_spinner_hydros = findViewById<Spinner>(R.id.spinner_hydro)
        val shopAdapter_hydros= ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,kg)
        drop_spinner_hydros.adapter = shopAdapter_hydros

    }
}