package com.example.golderform

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class StockDetails : AppCompatActivity() {
    val ShopName = arrayOf("SR","MSR","LC","SKA","OTHER")
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_details)

        val showbtn = findViewById<Button>(R.id.showbutton)

        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()
        val ns30_va= findViewById<TextView>(R.id.ns_30_val)
        val ns20_va = findViewById<TextView>(R.id.ns_20_val)
        val org_va= findViewById<TextView>(R.id.organic_val)
        val mns_va= findViewById<TextView>(R.id.mns_val)
        val njb_va= findViewById<TextView>(R.id.njb_val)
        val jb_va= findViewById<TextView>(R.id.jb_val)
        val jc_va= findViewById<TextView>(R.id.jc_val)
        val njc_va= findViewById<TextView>(R.id.njc_val)

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        tv.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                tv.setText(""+ mdayOfMonth+"-"+ mmonth+"-"+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        showbtn.setOnClickListener {
            val KottaiName: String = dropspinner.getSelectedItem().toString()

            database = FirebaseDatabase.getInstance().getReference("StockDetials")
            database.child(KottaiName).get().addOnSuccessListener{
                if(it.exists()){
                    var ns30  = it.child("NS30").value
                    var ns20 = it.child("NS20").value
                    var organic = it.child("ORGANIC").value
                    var mns = it.child("MNS").value
                    var njb = it.child("NJB").value
                    var jb = it.child("JB").value
                    var jc = it.child("JC").value
                    var njc = it.child("NJC").value

                    ns30_va.setText(ns30.toString()).toString()
                    ns20_va.setText(ns20.toString()).toString()
                    org_va.setText(organic.toString()).toString()
                    mns_va.setText(mns.toString()).toString()
                    njb_va.setText(njb.toString()).toString()
                    jb_va.setText(jb.toString()).toString()
                    jc_va.setText(jc.toString()).toString()
                    njc_va.setText(njc.toString()).toString()
                }
            }


        }
    }
}