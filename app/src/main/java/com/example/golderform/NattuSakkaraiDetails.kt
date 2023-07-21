package com.example.golderform

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class NattuSakkaraiDetails : AppCompatActivity() {

    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    private lateinit var database : DatabaseReference
    @RequiresApi(Build.VERSION_CODES.N)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nattu_sakkarai_details)
        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val bags = findViewById<EditText>(R.id.noofbags)
        val vechile = findViewById<EditText>(R.id.vechileid)
        val savebtn = findViewById<Button>(R.id.savebutton)

        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        tv.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                tv.setText(""+mdayOfMonth+"-"+mmonth+"-"+myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        database = FirebaseDatabase.getInstance().getReference("NattuSakkarai")
        savebtn.setOnClickListener{

            val NoofBags = bags.text.toString()
            val VechileNo = vechile.text.toString()
            val date = tv.text.toString()
            val KottaiName: String = dropspinner.getSelectedItem().toString()

            val details = NattuSakkarai(date,VechileNo,NoofBags,KottaiName)

            database.child(date).child(VechileNo).setValue(details)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Interset",Toast.LENGTH_SHORT).show()
                }
            bags.text.clear()
            vechile.text.clear()
        }
    }
}