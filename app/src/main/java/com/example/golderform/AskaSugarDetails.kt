package com.example.golderform

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AskaSugarDetails : AppCompatActivity() {

    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    private lateinit var database : DatabaseReference
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aska_sugar_details)

        // Load Database input fields
        val bags = findViewById<EditText>(R.id.noofbags)
        val vechile = findViewById<EditText>(R.id.vechileid)
        val savebtn = findViewById<Button>(R.id.savebutton)

        // Define Spinner and DatePicker
        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()

        // Define Calender Parameters
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        //Shop Name Spinner
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        //Select Date after clicking TextBox and Display the select date in TextBox
        tv.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                tv.setText(""+ mdayOfMonth +"-"+ mmonth +"-"+ myear)
            }, year, month, day)
            datePickerDialog.show()

        }

        //Define DataBase Connection in AskaSugar Node
        database = FirebaseDatabase.getInstance().getReference("AskaSugar")
        savebtn.setOnClickListener{

            val NoofBags = bags.text.toString()
            val VechileNo = vechile.text.toString()
            val date = tv.text.toString()
            val KottaiName: String = dropspinner.getSelectedItem().toString()

            val details = AskaDetials(date,VechileNo,NoofBags,KottaiName)

            //Insert Data into Database
            database.child(date).child(VechileNo).setValue(details)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Interset",Toast.LENGTH_SHORT).show()
                }
            bags.text.clear()
            vechile.text.clear()
        }
    }
}