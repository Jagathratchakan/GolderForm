package com.example.golderform

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Verification : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val verifybtn = findViewById<Button>(R.id.btnVerify)
        val progress = findViewById<ProgressBar>(R.id.progressBarVerify)
        val User_id= intent.getStringExtra("user_id")
        val signout = findViewById<Button>(R.id.button2)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val id = preferences.getString("userpre","")
        val input1 = findViewById<EditText>(R.id.etC1)
        val input2 = findViewById<EditText>(R.id.etC2)
        val input3 = findViewById<EditText>(R.id.etC3)
        val input4 = findViewById<EditText>(R.id.etC4)
        val input5 = findViewById<EditText>(R.id.etC5)
        val input6 = findViewById<EditText>(R.id.etC6)

        //val etC1 = findViewById<EditText>(R.id.etC1)
        input1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    // Move focus to the next EditText view
                    input2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
            }
        })

        //val etC2 = findViewById<EditText>(R.id.etC2)
        input2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    // Move focus to the next EditText view
                    input3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
                if (s?.isEmpty() == true) {
                    // Move focus to the previous EditText view
                    input1.requestFocus()
                }
            }
        })

        input3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    // Move focus to the next EditText view
                    input4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
                if (s?.isEmpty() == true) {
                    // Move focus to the previous EditText view
                    input2.requestFocus()
                }
            }
        })

        input4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    // Move focus to the next EditText view
                    input5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
                if (s?.isEmpty() == true) {
                    // Move focus to the previous EditText view
                    input3.requestFocus()
                }
            }
        })

        input5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    // Move focus to the next EditText view
                    input6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
                if (s?.isEmpty() == true) {
                    // Move focus to the previous EditText view
                    input4.requestFocus()
                }
            }
        })

        input6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
                if (s?.isEmpty() == true) {
                    // Move focus to the previous EditText view
                    input5.requestFocus()
                }
            }
        })



       /*verifybtn.setOnClickListener{
            val intentVar = Intent(this,AdminActicity::class.java)
            startActivity(intentVar)
        }
        input1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (TextUtils.isEmpty(input1.getText().toString())) {
                    input1.requestFocus()
                } else {
                    input2.requestFocus()
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })*/

        verifybtn.setOnClickListener {
            verifybtn.visibility = View.INVISIBLE
            progress.visibility = View.VISIBLE
            if (id != null) {

                database = FirebaseDatabase.getInstance().getReference("Users")
                database.child(id).get().addOnSuccessListener {
                    if(it.exists()){
                        val pin = it.child("PIN").value
                        val role = it.child("Role").value
                        if (input1.getText().toString().isEmpty()
                            || input2.getText().toString().isEmpty()
                            || input3.getText().toString().isEmpty()
                            || input4.getText().toString().isEmpty()
                            || input5.getText().toString().isEmpty()
                            || input6.getText().toString().isEmpty()){
                            Toast.makeText(
                                this,
                                "Please enter valid code",
                                Toast.LENGTH_SHORT).show()
                        }


                        var value : String = input1.getText().toString()+
                                input2.getText().toString()+
                                input3.getText().toString()+
                                input4.getText().toString()+
                                input5.getText().toString()+
                                input6.getText().toString()
                        if(pin==value){
                            if(role == "Admin"){
                                var value = value.drop(6)
                                verifybtn.visibility = View.VISIBLE
                                progress.visibility = View.INVISIBLE
                                /*val editor:SharedPreferences.Editor= preferences.edit()
                                editor.clear()
                                editor.apply()*/
                                val intentVar = Intent(
                                    this,
                                    AdminActicity::class.java)
                                startActivity(intentVar)
                                finish()
                            }
                            else if(role == "Manager"){
                                var value = value.drop(6)
                                /*val editor:SharedPreferences.Editor= preferences.edit()
                                editor.clear()
                                editor.apply()*/
                                verifybtn.visibility = View.VISIBLE
                                progress.visibility = View.INVISIBLE
                                val intentVar = Intent(
                                    this,
                                    ManagerActivity::class.java)
                                startActivity(intentVar)
                                finish()
                            }
                            else{
                                /*val editor:SharedPreferences.Editor= preferences.edit()
                                 editor.clear()
                                 editor.apply()*/
                                var value = value.drop(6)
                                verifybtn.visibility = View.INVISIBLE
                                progress.visibility = View.VISIBLE
                                val intentVar = Intent(
                                    this,
                                    UserActivity::class.java)
                                    startActivity(intentVar)
                                finish()
                            }

                        }else{
                            verifybtn.visibility = View.VISIBLE
                            progress.visibility = View.INVISIBLE
                            Toast.makeText(
                                this,
                                "Reenter PIN",
                                Toast.LENGTH_SHORT).show()
                            input1.text.clear()
                            input2.text.clear()
                            input3.text.clear()
                            input4.text.clear()
                            input5.text.clear()
                            input6.text.clear()
                            var value = value.drop(6)
                        }

                    }else{
                        Toast.makeText(
                            this,
                            "Please Login Again",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                verifybtn.visibility = View.VISIBLE
                progress.visibility = View.INVISIBLE
                val editor:SharedPreferences.Editor= preferences.edit()
                editor.clear()
                editor.apply()

                Toast.makeText(
                    this,
                    "Sign Out",
                    Toast.LENGTH_SHORT).show()
                val intentVar = Intent(
                    this,
                    MainActivity::class.java)
                startActivity(intentVar)
            }
        }

        signout.setOnClickListener {
            val editor:SharedPreferences.Editor= preferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(
                this,
                "Sign Out",
                Toast.LENGTH_SHORT).show()
            val intentVar = Intent(
                this,
                MainActivity::class.java)
            startActivity(intentVar)
        }


    }
}