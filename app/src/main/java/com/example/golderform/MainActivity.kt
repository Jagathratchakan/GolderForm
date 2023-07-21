package com.example.golderform

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    //private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // auth = Firebase.auth
        val progess = findViewById<ProgressBar>(R.id.progressBar)
        val greeting = findViewById<Button>(R.id.login)
        val email_id = findViewById<EditText>(R.id.emailid)
        val password_id = findViewById<EditText>(R.id.passwordid)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)


        progess.visibility = View.INVISIBLE
        /*greeting.setOnClickListener{

            // Verify the input field is empty
            if(email_id.text.isEmpty() || password_id.text.isEmpty()){
                Toast.makeText(this,"Please fill all the field",Toast.LENGTH_SHORT).show()
            }

            val emailinput = email_id.text.toString()
            val passwordinput = password_id.text.toString()

            // Authentication process by Email and Password
            auth.signInWithEmailAndPassword(emailinput, passwordinput)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // If sign in success, navigate to Verification page.
                        val intent = Intent(this,Verification::class.java).putExtra("email",emailinput)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }*/

        greeting.setOnClickListener {
            greeting.visibility = View.INVISIBLE
            progess.visibility = View.VISIBLE
            when {
                TextUtils.isEmpty(email_id.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter email.", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(password_id.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email: String = email_id.text.toString().trim { it <= ' ' }
                    val password: String = password_id.text.toString().trim { it <= ' ' }


                    //Log-in using FirebaseAuth
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "You are logged successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                /*if(FirstTime.equals("Yes")){
                                val intent = Intent(this,Verification::class.java).putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                startActivity(intent)
                                }
                                else{
                                    val editor = preferences.edit()
                                    editor.putString("FirstTimeLogin", "YES")
                                    editor.apply()
                                }*/
                                greeting.visibility = View.VISIBLE
                                progess.visibility = View.INVISIBLE

                                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                                val user : String = FirebaseAuth.getInstance().currentUser!!.uid
                                editor.putString("userpre",user)
                                editor.apply()
                                val intent = Intent(this,Verification::class.java).putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                startActivity(intent)

                                finish()

                            } else {
                                //If the login is not successful then show error message.
                                greeting.visibility = View.VISIBLE
                                progess.visibility = View.INVISIBLE
                                Toast.makeText(
                                    this,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                                email_id.text.clear()
                                password_id.text.clear()
                            }
                        }
                }
            }
        }
    }
}
