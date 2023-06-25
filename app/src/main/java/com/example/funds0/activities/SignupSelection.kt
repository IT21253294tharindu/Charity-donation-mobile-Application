package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.funds0.R

class SignupSelection : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_selection)

        //charity registration page linking
        val CharitysignUpbutton = findViewById<Button>(R.id.btnCharitySignup)
        CharitysignUpbutton.setOnClickListener {
            val intent = Intent(this, Charity_RegisterationForm::class.java)
            startActivity(intent)
        }
        //Back button
        val BackBtn = findViewById<Button>(R.id.btnBackSingup)
        BackBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}