package com.example.golderform

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class OtherDetails : AppCompatActivity() {

    val ShopName = arrayOf("SR","MSR","LC","SKA","Others2")
    val work = arrayOf("Male","Female")

    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_details)

        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val Oil_tins = findViewById<EditText>(R.id.no_oil_tins)
        val soda = findViewById<EditText>(R.id.no_soda)
        val kuranai = findViewById<EditText>(R.id.no_kurunai)
        val sunambu = findViewById<EditText>(R.id.no_suname)
        val mattai = findViewById<EditText>(R.id.no_mattai)
        val hydros = findViewById<EditText>(R.id.no_hydros)
        val No_workers = findViewById<EditText>(R.id.no_workers)
        val savebtn = findViewById<Button>(R.id.savebutton)

        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        val drop_spinner_worker = findViewById<Spinner>(R.id.spinner_worker)
        val shopAdapter_worker= ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,work)
        drop_spinner_worker.adapter = shopAdapter_worker


        tv.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                tv.setText(""+ mdayOfMonth+"-"+ mmonth+"-"+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        database = FirebaseDatabase.getInstance().getReference("OtherDetials")
        savebtn.setOnClickListener{


            val date = tv.text.toString()
            val No_oil_tins = Oil_tins.text.toString()
            val No_soda = soda.text.toString()
            val No_kuranai = kuranai.text.toString()
            val No_sunambu = sunambu.text.toString()
            val No_mattai = mattai.text.toString()
            val hydro_no = hydros.text.toString()
            val No_worker = No_workers.text.toString()

            val KottaiName: String = dropspinner.getSelectedItem().toString()
            val Gender_Work: String = drop_spinner_worker.getSelectedItem().toString()

            val details = OtherDetials(date,KottaiName,No_oil_tins,No_soda,No_kuranai,No_sunambu,No_mattai,hydro_no,No_worker,Gender_Work)

            database.child(date).child(KottaiName).setValue(details)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Interset",Toast.LENGTH_SHORT).show()
                }

            Oil_tins.text.clear()
            No_workers.text.clear()
            soda.text.clear()
            kuranai.text.clear()
            sunambu.text.clear()
            mattai.text.clear()
            hydros.text.clear()


        }
    }
}