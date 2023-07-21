package com.example.golderform

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AllaiRunningDetails : AppCompatActivity() {

    val ShopName = arrayOf("SR", "MSR", "LC", "SKA", "Others")
    val itemname = arrayOf("NS20", "NS30", "ORGANIC", "MNS", "NJB", "NJC", "JB", "JC")
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allai_running_details)
        val deliveritem = findViewById<EditText>(R.id.Person_delivery)
        val savebtn = findViewById<Button>(R.id.savebutton)

        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val shopAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ShopName)
        dropspinner.adapter = shopAdapter


        val itemsspinner = findViewById<Spinner>(R.id.delivery_item)
        val itemAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemname)
        itemsspinner.adapter = itemAdapter



        val time = LocalDateTime.now()
        val form = DateTimeFormatter.ofPattern("HH-mm-ss")
        val timeNow = form.format(time).toString()

        tv.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                    tv.setText("" + mdayOfMonth + "-" + mmonth + "-" + myear)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        database = FirebaseDatabase.getInstance().getReference("AllaiRunning")
        savebtn.setOnClickListener {

            val KottaiName: String = dropspinner.getSelectedItem().toString()
            val itemName: String = itemsspinner.getSelectedItem().toString()

            val items = deliveritem.text.toString()
            val date = tv.text.toString()
            val details = AllaiRunning(date, items, itemName, KottaiName)
            //Insert Data into Database
            database.child(date).child(timeNow).setValue(details)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data Interset", Toast.LENGTH_SHORT).show()
                }
            deliveritem.text.clear()
            database = FirebaseDatabase.getInstance().getReference("StockDetials")
            database.child(KottaiName).get().addOnSuccessListener {
                if (it.exists()) {
                    var value1 = it.child(itemName).value.toString()
                    var update = items.toInt()+value1.toInt()

                    val updatevale = mapOf(
                        itemName to update.toInt()
                    )
                    database.child(KottaiName).updateChildren(updatevale)
                    Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
                }


            }
        }
    }
}