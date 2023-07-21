package com.example.golderform

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class SugarcaneDetails : AppCompatActivity() {
    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    val vettal_name = arrayOf("Arumugam","Vellaiammal","Others1","Others2")
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugarcane_details)
        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        val dropspinner_vettal = findViewById<Spinner>(R.id.spinner2)
        val shopAdapter_vettal = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,vettal_name)
        dropspinner_vettal.adapter = shopAdapter_vettal

        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val s_no_ = findViewById<EditText>(R.id.s_no)
        val vechile = findViewById<EditText>(R.id.vechileid)
        val total_tone = findViewById<EditText>(R.id.Tone)
        val name_of_person = findViewById<EditText>(R.id.Name)
        val savebtn = findViewById<Button>(R.id.save_button)

        tv.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                tv.setText(""+ mdayOfMonth +"-"+ mmonth +"-"+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        database = FirebaseDatabase.getInstance().getReference("Sugarcane")
        savebtn.setOnClickListener{


            val date = tv.text.toString()
            val S_No = s_no_.text.toString()
            val Vechile_number = vechile.text.toString()
            val Total_tone= total_tone.text.toString()
            val Name = name_of_person.text.toString()

            val KottaiName: String = dropspinner.getSelectedItem().toString()
            val Gender_Work: String = dropspinner_vettal.getSelectedItem().toString()

            val details = Sugarcane(date,KottaiName,S_No,Vechile_number,Total_tone,Name,Gender_Work)

            database.child(date).child(KottaiName).setValue(details)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Interset",Toast.LENGTH_SHORT).show()
                }

            s_no_.text.clear()
            vechile.text.clear()
            total_tone.text.clear()
            name_of_person.text.clear()
        }

    }
}