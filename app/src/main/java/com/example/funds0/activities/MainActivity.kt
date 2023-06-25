package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.funds0.LogIn
import com.example.funds0.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var charitybtn =findViewById<Button>(R.id.charity)
        charitybtn.setOnClickListener {
            val intent=Intent(this, Charity_Login::class.java)
            startActivity(intent)
        }
        var donorbtn= findViewById<Button>(R.id.donors)
        donorbtn.setOnClickListener{
            val intent=Intent(this,LogIn::class.java)
            startActivity(intent)
        }
    }
}