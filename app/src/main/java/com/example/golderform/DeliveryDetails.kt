package com.example.golderform

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DeliveryDetails : AppCompatActivity() {
    val ShopName = arrayOf("SR","MSR","LC","SKA","Others")
    val itemname = arrayOf("NS20","NS30","ORGANIC","MNS","NJB","NJC","JB","JC")
    private var parentLinearLayout: LinearLayout? = null
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)
        val vechile = findViewById<EditText>(R.id.Person_delivery)
        val savebtn = findViewById<Button>(R.id.savebutton)

        //parentLinearLayout = findViewById(R.id.parent_linear_layout)

        // Define Spinner and DatePicker
        val dropspinner = findViewById<Spinner>(R.id.spinner)
        val itemsspinner = findViewById<Spinner>(R.id.delivery_item)
        val tv = findViewById<TextView>(R.id.date)
        val cal = Calendar.getInstance()



        // Define Calender Parameters
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val time = LocalDateTime.now()
        val form = DateTimeFormatter.ofPattern("HH-mm-ss")
        val timeNow = form.format(time).toString()

        //Shop Name Spinner
        val shopAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ShopName)
        dropspinner.adapter = shopAdapter

        val itemAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,itemname)
        itemsspinner.adapter = itemAdapter



        //Select Date after clicking TextBox and Display the select date in TextBox
        tv.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                tv.setText(" "+ mdayOfMonth +"-"+ mmonth +"-"+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        database = FirebaseDatabase.getInstance().getReference("DeliveryDetials")
        savebtn.setOnClickListener{

            val KottaiName: String = dropspinner.getSelectedItem().toString()
            val itemName: String = itemsspinner.getSelectedItem().toString()
            val VechileNo = vechile.text.toString()
            val date = tv.text.toString()

            val details =DeliveryDetials(date,VechileNo,itemName,KottaiName)

            //Insert Data into Database
            database.child(date).child(timeNow).setValue(details)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Interset", Toast.LENGTH_SHORT).show()
                }
            vechile.text.clear()
        }


    }
    fun onDelete(view: View) {
        parentLinearLayout!!.removeView(view.parent as View)
    }
    fun onAddField(view: View) {
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.field, null)
        parentLinearLayout!!.addView(rowView, parentLinearLayout!!.childCount - 1)
    }
}