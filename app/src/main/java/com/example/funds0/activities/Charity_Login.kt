package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.funds0.R
import com.example.funds0.activities.SignupSelection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Charity_Login : AppCompatActivity() {

    private lateinit var emailInput:EditText
    private lateinit var passwinout:EditText
    val auth=FirebaseAuth.getInstance()
    val user= Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity_login)

        emailInput=findViewById<EditText>(R.id.usernInput)
        passwinout=findViewById<EditText>(R.id.passwInput)

        //signup page linking
        val signUpbutton = findViewById<Button>(R.id.signUpBtn)
        signUpbutton.setOnClickListener {
            val intent = Intent(this, Charity_RegisterationForm::class.java)
            startActivity(intent)
        }

        //logInButton
        val logBtn = findViewById<Button>(R.id.logInBtn)
        logBtn.setOnClickListener {
           login()
        }

    }

    private fun login() {
        val emails=emailInput.text.toString()
        val passw=passwinout.text.toString()

        if(user==null){
            if(emails.isEmpty() || passw.isEmpty()){
                Toast.makeText(this, "Please Enter Your LogIn Details...", Toast.LENGTH_SHORT).show()
            }

            auth.signInWithEmailAndPassword(emails, passw).addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Log in Successful..", Toast.LENGTH_SHORT).show()
                    val charityProfile=Intent(this, Charity_Profile::class.java)
                    startActivity(charityProfile)
                }else{
                    emailInput.text.clear()
                    passwinout.text.clear()
                    Toast.makeText(this, "Log in Unsuccessful... Try Again", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(this, "You're already logged in", Toast.LENGTH_SHORT).show()
            val toProf=Intent(this, Charity_Profile::class.java)
            startActivity(toProf)
        }


    }
}