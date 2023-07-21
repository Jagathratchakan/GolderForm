package com.example.golderform

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MattaiDetails : AppCompatActivity() {
    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mattai_details)
        val dropspinner = findViewById<Spinner>(R.id.spinner)

        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val trips = findViewById<EditText>(R.id.trips)
        val savebtn = findViewById<Button>(R.id.savebutton)

        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        tv.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                tv.setText(""+ mdayOfMonth +"-"+ mmonth +"-"+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        database = FirebaseDatabase.getInstance().getReference("MattaiDetials")
        savebtn.setOnClickListener{

            val NoofTrips = trips.text.toString()
            val date = tv.text.toString()
            val KottaiName: String = dropspinner.getSelectedItem().toString()

            val details = MattaiDetials(date,NoofTrips,KottaiName)

            database.child(date).child(KottaiName).setValue(details)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Interset",Toast.LENGTH_SHORT).show()
                }
            trips.text.clear()
        }
    }
}